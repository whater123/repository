<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>登录</title>
    <script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
</head>
<body onload="init()">
<form action="/getMailCode" method="post">
    请输入注册邮箱：<input type="text" name="userMail">
    <input type="submit" value="获取验证码">
</form>
<form action="/submit" method="post">
    请输入注册邮箱：<input type="text" name="userMail">
    输入验证码(不区分大小写)：<input type="text" name="code">
    <input type="submit" value="确定">
</form>
<template>
    <div class="amap-page-container">
        <el-amap vid="amap" :plugin="plugin" class="amap-demo" :center="center">
        </el-amap>
        ​
        <div class="toolbar">
        <span v-if="loaded">
          location: lng = {{ lng }} lat = {{ lat }}
        </span>
            <span v-else>正在定位</span>
        </div>
    </div>
</template>
​
<style>
    .amap-demo {
        height: 300px;
    }
</style>
​
<script>
    module.exports = {
        data() {
            let self = this;
            return {
                center: [121.59996, 31.197646],
                lng: 0,
                lat: 0,
                loaded: false,
                plugin: [{
                    pName: 'Geolocation',
                    events: {
                        init(o) {
                            // o 是高德地图定位插件实例
                            o.getCurrentPosition((status, result) => {
                                if (result && result.position) {
                                    self.lng = result.position.lng;
                                    self.lat = result.position.lat;
                                    self.center = [self.lng, self.lat];
                                    self.loaded = true;
                                    self.$nextTick();
                                }
                            });
                        }
                    }
                }]
            };
        }
    };
</script>
</body>
</html>