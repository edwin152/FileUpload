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

                if (data.code === 1) {
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
        formData.append('uploadImage', obj.file);

        $.ajax({
            type: "post",
            url: obj.url,
            contentType: false,
            data: formData,
            processData: false,
            success: function (data) {
                data = JSON.parse(data);
                console.log(data);

                if (data.code === 1) {
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
    }
};