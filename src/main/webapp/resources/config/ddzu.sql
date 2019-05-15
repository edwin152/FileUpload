GRANT ALL PRIVILEGES ON SHOP.* TO 'ddzu'@'%' IDENTIFIED BY 'ddzu';
GRANT ALL PRIVILEGES ON SHOP.* TO 'ddzu'@'localhost' IDENTIFIED BY 'ddzu';

DROP DATABASE SHOP;

CREATE DATABASE SHOP;

USE SHOP;

CREATE TABLE DISTRICT
(
    ID   INT(11) AUTO_INCREMENT,
    NAME VARCHAR(255),
    PRIMARY KEY (ID)
) DEFAULT CHARSET = UTF8;
INSERT INTO DISTRICT(NAME)
VALUES ('全部'),
       ('浦东'),
       ('黄浦'),
       ('卢湾'),
       ('徐汇'),
       ('长宁'),
       ('静安'),
       ('普陀'),
       ('闸北'),
       ('虹口'),
       ('杨浦'),
       ('闵行'),
       ('宝山'),
       ('嘉定'),
       ('松江'),
       ('青浦'),
       ('奉贤'),
       ('上海周边'),
       ('南汇');

CREATE TABLE ZONE
(
    ID          INT(11) AUTO_INCREMENT,
    DISTRICT_ID INT(11),
    NAME        VARCHAR(255),
    PRIMARY KEY (ID)
) DEFAULT CHARSET = UTF8;
INSERT INTO ZONE(DISTRICT_ID, NAME)
VALUES (1, '全部'),
       (2, '全部'),
       (3, '全部'),
       (4, '全部'),
       (5, '全部'),
       (6, '全部'),
       (7, '全部'),
       (8, '全部'),
       (9, '全部'),
       (10, '全部'),
       (11, '全部'),
       (12, '全部'),
       (13, '全部'),
       (14, '全部'),
       (15, '全部'),
       (16, '全部'),
       (17, '全部'),
       (18, '全部'),
       (19, '全部'),

       (2, '八佰伴'),
       (2, '北蔡/塘桥'),
       (2, '川沙'),
       (2, '金桥开发区'),
       (2, '陆家嘴'),
       (2, '南码头'),
       (2, '浦东外环'),
       (2, '三林'),
       (2, '上南地区'),
       (2, '世博滨江'),
       (2, '世纪公园'),
       (2, '外高桥'),
       (2, '洋泾'),
       (2, '御桥金桥'),
       (2, '高行'),
       (2, '张江高科'),
       (2, '竹园商贸区'),
       (3, '董家渡'),
       (3, '老西门'),
       (3, '南京东路'),
       (3, '人民广场'),
       (3, '外滩'),
       (4, '打浦桥'),
       (4, '淮海中路'),
       (4, '五里桥/鲁班路'),
       (4, '新天地'),
       (5, '漕河泾'),
       (5, '淮海西路'),
       (5, '龙华'),
       (5, '上海南站'),
       (5, '万体馆'),
       (5, '徐家汇'),
       (6, '北新泾'),
       (6, '虹桥火车站'),
       (6, '虹桥开发区/古北'),
       (6, '天山路'),
       (6, '延安西路'),
       (6, '中山公园'),
       (7, '北京西路'),
       (7, '曹家渡'),
       (7, '静安寺'),
       (7, '南京西路/江宁路'),
       (8, '甘泉宜川'),
       (8, '中远两湾城'),
       (8, '中山北路'),
       (8, '长风商务区'),
       (8, '长寿路'),
       (8, '真如/李子园'),
       (9, '大宁/延长路'),
       (9, '火车站'),
       (9, '汶水路/共和新路'),
       (9, '彭浦'),
       (9, '闸北公园'),
       (10, '北外滩'),
       (10, '大柏树'),
       (10, '凉城路/虹口足球场'),
       (10, '临平/和平公园'),
       (10, '曲阳'),
       (10, '四川北路'),
       (10, '四平路'),
       (11, '四平路'),
       (11, '鞍山'),
       (11, '控江路'),
       (11, '周家嘴'),
       (11, '东外滩'),
       (11, '平凉'),
       (11, '长阳路'),
       (11, '五角场'),
       (11, '新江湾城'),
       (11, '中原'),
       (12, '七宝'),
       (12, '七莘路'),
       (12, '莘庄'),
       (12, '颛桥/老闵行'),
       (12, '春申'),
       (12, '虹桥镇'),
       (12, '华漕'),
       (12, '南方商城'),
       (12, '浦江'),
       (12, '龙柏金汇'),
       (12, '其他'),
       (13, '淞南高境'),
       (13, '顾村'),
       (13, '共康'),
       (13, '共富'),
       (13, '大华'),
       (13, '宝山其他'),
       (14, '安亭'),
       (14, '丰庄'),
       (14, '嘉定城区'),
       (14, '江桥'),
       (14, '南翔'),
       (14, '嘉定其他'),
       (15, '九亭/新桥'),
       (15, '泗泾'),
       (15, '松江新城/松江大学城'),
       (15, '松江其他'),
       (16, '青浦新城'),
       (16, '青浦其他'),
       (19, '全部'),
       (19, '周浦'),
       (19, '康桥'),
       (19, '航头');

CREATE TABLE METRO
(
    ID   INT(11) AUTO_INCREMENT,
    NAME VARCHAR(255),
    PRIMARY KEY (ID)
) DEFAULT CHARSET = UTF8;
INSERT INTO METRO(NAME)
VALUES ('全部'),
       ('1号线'),
       ('2号线'),
       ('4号线'),
       ('5号线'),
       ('6号线'),
       ('7号线'),
       ('8号线'),
       ('9号线'),
       ('13号线'),
       ('16号线'),
       ('10号线主线'),
       ('10号线支线'),
       ('11号线主线'),
       ('11号线支线'),
       ('12号线3号线'),
       ('磁悬浮'),
       ('17号线');

CREATE TABLE TYPE
(
    ID   INT(11) AUTO_INCREMENT,
    NAME VARCHAR(255),
    PRIMARY KEY (ID)
) DEFAULT CHARSET = UTF8;
INSERT INTO TYPE(NAME)
VALUES ('全部'),
       ('普通办公'),
       ('联合办公'),
       ('创意园区');

CREATE TABLE AREA_RANGE
(
    ID   INT(11) AUTO_INCREMENT,
    NAME VARCHAR(255),
    PRIMARY KEY (ID)
) DEFAULT CHARSET = UTF8;
INSERT INTO AREA_RANGE(NAME)
VALUES ('全部'),
       ('0-100m²'),
       ('100-300m²'),
       ('300-500m²'),
       ('500-1000m²'),
       ('1000m²以上');

CREATE TABLE PRICE_RANGE
(
    ID   INT(11) AUTO_INCREMENT,
    NAME VARCHAR(255),
    PRIMARY KEY (ID)
) DEFAULT CHARSET = UTF8;
INSERT INTO PRICE_RANGE(NAME)
VALUES ('全部'),
       ('0-3元/m²/天'),
       ('3-4元/m²/天'),
       ('4-5元/m²/天'),
       ('5-7元/m²/天'),
       ('7-9元/m²/天'),
       ('9-12元/m²/天'),
       ('12元/m²/天以上');

CREATE TABLE DECORATION
(
    ID   INT(11) AUTO_INCREMENT,
    NAME VARCHAR(255),
    PRIMARY KEY (ID)
) DEFAULT CHARSET = UTF8;
INSERT INTO DECORATION(NAME)
VALUES ('全部'),
       ('豪华装修'),
       ('精装修'),
       ('中等装修'),
       ('简装修'),
       ('毛坯');

CREATE TABLE BUILDING
(
    ID              INT(11) AUTO_INCREMENT,
    NAME            VARCHAR(255),
    ZONE_ID         INT(11),
    ADDRESS         VARCHAR(255),
    METRO_NAME_LIST VARCHAR(255),
    INTRODUCE       VARCHAR(2048),
    NOTES           VARCHAR(8192),
    IMG_LIST        VARCHAR(8192),
    PRIMARY KEY (ID)
) DEFAULT CHARSET = UTF8;

CREATE TABLE OFFICE
(
    ID               INT(11) AUTO_INCREMENT,
    NAME             VARCHAR(255),
    BUILDING_ID      INT(11),
    ADDRESS          VARCHAR(255),
    TYPE_ID          INT(11),
    AREA             FLOAT,
    AREA_RANGE_ID    INT(11),
    PRICE            FLOAT,
    PRICE_RANGE_ID   INT(11),
    DECORATION_ID    INT(11),
    UTILIZATION_RATE FLOAT,
    CAN_REGISTER     BOOLEAN,
    RENT_FREE_PERIOD VARCHAR(255),
    NOTES            VARCHAR(8192),
    IMG_LIST         VARCHAR(8192),
    PRIMARY KEY (ID)
) DEFAULT CHARSET = UTF8;

SHOW TABLES;
