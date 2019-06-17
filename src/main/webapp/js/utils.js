'use strict';

let http = {

    post: function (obj) {
        let data = {};
        for (let key in obj.params) {
            if (!obj.params.hasOwnProperty(key)
                || !obj.params[key]) {
                continue;
            }
            let value = obj.params[key];
            if (typeof (value) === "string") {
                data[key] = obj.params[key];
            } else {
                data[key] = JSON.stringify(obj.params[key]);
            }
        }

        console.log(data);

        $.ajax({
            type: "post",
            url: obj.url,
            dataType: "Json",
            data: data,
            success: function (data) {
                console.log(data);

                if (data.code === 0) {
                    if (!obj.success) return;
                    obj.success(data.data);
                } else {
                    alert(data.code + ":" + data.msg);

                    if (!obj.onError) return;
                    obj.onError(code, msg);
                }
            },
            error: function () {
                console.log(data);
            }
        });

    },

    getParameter: function (request, key) {
        if (!request
            || !key
            || request.length === 0
            || key.length === 0) {
            return null;
        }

        request = request.substring(1);

        let index = request.indexOf(key);
        if (index === -1) return null;
        while (index !== 0 && request[index - 1] !== "&") {
            index = request.indexOf(key, index + 1);
            if (index === -1) return null;
        }

        let end = request.indexOf("&", index);
        if (end === -1) {
            return request.substring(index + key.length + 1);
        } else {
            return request.substring(index + key.length + 1, end);
        }
    },

    upload: function (obj) {
        let formData = new FormData();
        formData.append('file', obj.file);

        $.ajax({
            type: "post",
            url: obj.url,
            contentType: false,
            data: formData,
            processData: false,
            success: function (data) {
                data = JSON.parse(data);
                console.log(data);

                if (data.code === 0) {
                    if (!obj.success) return;
                    obj.success(data.data);
                } else {
                    alert(data.code + ":" + data.msg);

                    if (!obj.onError) return;
                    obj.onError(code, msg);
                }
            },
            error: function (e) {
                console.log(e);
            }
        });
    },
};

let utils = {
    default_img: "http://www.orangeban.com/images/default.jpg",

    getImg_list: function (obj) {
        return obj && obj.img_list && obj.img_list.length > 0
            ? obj.img_list
            : [this.default_img];
    },

    setImage: function (img, img_list) {
        let obj = {
            img_list: img_list,
        };
        let imgList = this.getImg_list(obj);
        img.setAttribute("src", imgList[0]);
        let id = img.id;
        $("#" + id).show();
    },

    setImageSrc: function (img, src) {
        if (!src) {
            let img_list = this.getImg_list();
            src = img_list[0];
        }
        img.setAttribute("src", src);
        img.setAttribute("alt", "橙办");
    },
};


function getScrollOffset() {
    if (window.pageXOffset !== undefined) {
        return {
            x: window.pageXOffset,
            y: window.pageYOffset
        }
    } else {
        return {
            x: document.body.scrollLeft + document.documentElement.srollLeft,
            y: document.body.scrollTop + document.documentElement.srollTop
        }
    }
}