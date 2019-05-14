let http = {
    post: function (obj) {
        let data = {};
        for (let key in obj.params) {
            if (!obj.params.hasOwnProperty(key)) {
                continue;
            }
            let value = obj.params[key];
            if (typeof (value) === "string") {
                data[key] = obj.params[key];
            } else {
                data[key] = JSON.stringify(obj.params[key]);
            }
        }

        $.ajax({
            type: "post",
            url: obj.url,
            dataType: "Json",
            data: data,
            success: function (data) {
                if (obj.onSuccess) {
                    obj.onSuccess(data);
                }
            },
            error: function () {
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
};