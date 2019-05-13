let http = {
    post: function (obj) {
        let xmlHttp = new XMLHttpRequest();
        xmlHttp.onreadystatechange = function () {
            if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
                if (obj.onSuccess) {
                    obj.onSuccess(xmlHttp.responseText);
                }
            }
        };
        xmlHttp.charset = "utf-8";
        xmlHttp.open("POST", obj.url, true);
        xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

        let params = obj.params;
        let p = "";
        if (params || params === null) {
            xmlHttp.send();
        } else {
            for (let key in params) {
                // noinspection JSUnfilteredForInLoop
                let value = params[key];
                if (value != null) {
                    if (p.length !== 0) {
                        p += "&";
                    }
                    p += key + "=" + value;
                }
            }
            if (p === "") {
                p = undefined;
            }
            xmlHttp.send(p);
        }
    }
};