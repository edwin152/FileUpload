<%--suppress HtmlFormInputWithoutLabel --%>
<%--suppress JSJQueryEfficiency --%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>楼列表</title>
    <link rel="stylesheet" type="text/css" href="../css/manage.css"/>
    <script src="../js/jquery-1.12.0.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="../js/utils.js" type="text/javascript" charset="utf-8"></script>
</head>

<script type="text/javascript">
    'use strict';

    let keyword;
    let filter = {};
    let pageIndex = 0;
    let pageNum = 0;

    window.onload = function () {
        getFilterList();
    };

    function getFilterList() {
        http.post({
            url: "../filter/newsTag",
            success: function (data) {
                filter = data;
                getNewsList();
            }
        });
    }

    function getNewsList(page) {
        http.post({
            url: "../edit/newsList",
            params: {
                news_tag_id: filter.checkedNewsTagId,
                page: page,
            },
            success: function (data) {
                filter.checkedNewsTagId = data.checkedNewsTagId;
                setFilterList();
                pageIndex = data.pageIndex;
                pageNum = data.pageNum;
                setNewsList(data.newsList);
            },
        });
    }

    function setFilterList() {
        if (!filter) {
            return;
        }
        setFilterListByName(filter.newsTagList, 'newsTagList', filter.checkedNewsTagId);
    }

    function setFilterListByName(list, name, checkedId) {
        if (!list || list.length < 2) {
            $("#" + name.substr(0, name.length - 4)).hide();
        } else {
            $("#" + name.substr(0, name.length - 4)).show();
        }
        let conditionBox = document.getElementById(name);
        conditionBox.innerHTML = "";
        for (let i = 0; list && i < list.length; i++) {
            let optionItem = document.createElement("span");
            if (list[i].id === checkedId) {
                optionItem.className = 'choose_value_positive';
            } else {
                optionItem.className = 'choose_value';
            }
            optionItem.innerHTML = list[i].name;
            optionItem.onclick = function () {
                filter.checkedNewsTagId = list[i].id;
                getNewsList();
            };
            conditionBox.appendChild(optionItem);
        }
    }

    function setNewsList(newsList) {
        let data_list = document.getElementById("data_list");
        if (pageIndex === 0) {
            data_list.innerHTML = "";
        }
        if (pageNum > pageIndex + 1) {
            $("#next_page").show();
        } else {
            $("#next_page").hide();
        }

        for (let news of newsList) {
            let conditionBox = document.createElement("span");
            conditionBox.className = "box row";
            conditionBox.innerHTML = news.title
                + (news.sub_title ? ("(" + news.sub_title + ")") : "")
                + (news.news_tag_name ? ("&nbsp;&nbsp;&nbsp;标签:" + news.news_tag_name) : "")
                + (news.hot ? "&nbsp;&nbsp;&nbsp;热门" : "&nbsp;&nbsp;&nbsp;普通")
                + "<br>创建时间:" + news.create_time
                + "<br>最近修改时间:" + news.last_edit_time;
            data_list.appendChild(conditionBox);


            let button_list = document.createElement("div");
            button_list.className = "column";
            conditionBox.appendChild(button_list);

            let edit_building = document.createElement("button");
            edit_building.innerHTML = "编辑";
            edit_building.onclick = function () {
                window.open("editNews?news_id=" + news.id, "_blank");
            };
            button_list.appendChild(edit_building);

            let delete_building = document.createElement("button");
            delete_building.innerHTML = "删除";
            delete_building.onclick = function () {
                deleteNews(news.id, conditionBox);
            };
            button_list.appendChild(delete_building);

        }
    }

    function nextPage() {
        getNewsList(pageIndex + 1);
    }

    function openDetail(news_id) {
        window.open("../newsDetail.html?news_id=" + news_id, "_blank");
    }

    function deleteNews(news_id, view) {
        http.post({
            url: "../edit/deleteNews",
            params: {
                id: news_id,
            },
            success: function () {
                let parent = view.parentElement;
                parent.removeChild(view);
            }
        });
    }

    function addNews() {
        window.open("editNews", "_blank");
    }
</script>

<body>
<div class="box">
    <div class="row">
        <span class="key">标签：</span>

        <div id="newsTagList"></div>
    </div>
</div>

<div class="submit" onclick="addNews()">新增</div>

<div id="data_list"></div>

<div class="submit" id="next_page" onclick="nextPage()">下一页</div>
</body>
</html>