var k_protocol = window.location.protocol;
/**
 * 电影详细信息
 */
myApp.controller('movieCtrl',function ($scope,$http) {

    $http({
        method: 'GET',
        url: k_protocol+'/movie/'+0+window.sessionStorage.getItem("movieId"),
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
    }).then(function successCallback(res) {
        var ob = JSON.parse(JSON.stringify(res.data));
        if(200==ob.code) {
            $scope.img = ob.data.img;
            $scope.titlecn = ob.data.titlecn;
            $scope.titleen = ob.data.titleen;
            $scope.type = ob.data.type;
            $scope.moviesTime = ob.data.time;
            $scope.director = ob.data.director;
            $scope.story = ob.data.story;
            // $scope.actors = ob.data.actors;
            $scope.actors=ob.data.actors.split(" ")


            $('#readOnly-demo').raty({
                readOnly: true,
                score: ob.data.rating
            });
        }

    },function errorCallback(res) {
        console.log(res);
    })

    /**
     * 点击演员姓名
     * @param name
     */
    $scope.actorInfo=function (name) {
        $http({
            method: 'GET',
            url: k_protocol+'/actorName/' + name,
            headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        }).then(function successCallback(res) {
            var ob = JSON.parse(JSON.stringify(res.data));
            if(200==ob.code) {
                window.sessionStorage.setItem("actorId", ob.data.list[0].actorid);
                window.location.href = 'actor-info';
            }else {
                $scope.message = ob.message;
                $('#actor-alert').modal()
            }

        },function errorCallback(res) {
            console.log(res);
        });
    }
})

/**
 * 评论
 */
myApp.controller('remarkCtrl',function ($scope,$http) {

    var movieId=window.sessionStorage.getItem("movieId");
    var userId;
    if(window.sessionStorage.getItem("userid") == null) {
        userId = 0;
        console.log("userid:" + userId);
    }else {
        userId=window.sessionStorage.getItem("userid");
    }


    getMovieRemark();

    /**
     * 添加评论
     */
    $scope.remark=function () {
        console.log('评论分数 ' + s);
        var remark= {
            "content":$scope.content,
            "score":s,
            "movieId":movieId,
            "userId":userId}
        $http({
            method: 'POST',
            url:k_protocol+'/remark',
            data:$.param(remark),
            headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
        }).then(function successCallback(res) {
            var ob = JSON.parse(JSON.stringify(res.data));
            if(200==ob.code){
                $('#successRemark-alert').modal();
                getMovieRemark();
                $scope.content = "";
            }else {
                console.log(ob)
                $('#errorRemark-alert').modal();
            }
        },function errorCallback(res) {
            console.log(res)
            $('#errorRemark-alert').modal();
        });
    }

    /**
     * 获取电影评论
     */
    function getMovieRemark() {
        $http({
            method:'GET',
            url:k_protocol+'/remark/'+window.sessionStorage.getItem("movieId")+'/'+userId,
            headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
        }).then(function successCallback(res) {
            var ob = JSON.parse(JSON.stringify(res.data));
            if (200==ob.code){
                $scope.remarks = ob.data;
                var remarks =  $scope.remarks;
                window.onload = function () {
                    console.log(remarks.length);
                    for(var i = 0; i < remarks.length; i++) {
                        // 判断是否登陆
                        if(remarks[i].userLike != null) {
                            if(remarks[i].userLike.status == 1) {
                                console.log(remarks[i].userLike.status);
                                $("#like" + remarks[i].remarkid).addClass("heartAnimation").attr("rel","unlike");
                                $("#like" + remarks[i].remarkid).css("background-position","right");
                            }
                        }

                    }

                }
            }
        },function errorCallback(res) {
            console.log(res)
        });
    }

    /**
     * 查询点赞信息
     */
    $scope.findUserlike = function (remarkId) {
        console.log("remarkid:" + remarkId);
        console.log("userid:" + userId);
        if(remarkId == null || userId == null || userId == 0) {
            // layui
            layui.use(['layer', 'form'], function(){
                    var layer = layui.layer
                        ,form = layui.form;
                    layer.msg("点赞失败,请先登陆！");
            });
        }else {
            $http({
                method:'GET',
                url:k_protocol+'/remark/like',
                params: {"remarkId": remarkId,"userId": userId},
                headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
            }).then(function successCallback(res) {
                var ob = JSON.parse(JSON.stringify(res.data));
                if (200==ob.code){
                    console.log("点赞信息查询成功!");
                    if(ob.data == null) {
                        console.log("没有这条点赞信息！");
                        var userLike= {
                            "likeRemarkid":remarkId,
                            "likeUserid":userId,
                            "status":1}
                        $http({
                            method:'POST',
                            url:k_protocol+'/remark/like',
                            data:$.param(userLike),
                            headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
                        }).then(function successCallback(res) {
                            var ob = JSON.parse(JSON.stringify(res.data));
                            console.log("点赞信息添加成功！");
                            $scope.msgTips(ob,remarkId);
                        },function errorCallback(res) {
                            // layui
                            layui.use(['layer', 'form'], function(){
                                var layer = layui.layer
                                    ,form = layui.form;
                                layer.msg("点赞失败");
                            });
                            console.log(res);
                        });
                    }else {
                        var status = ob.data.status;
                        console.log("有点赞信息，点赞状态为:" + status);
                        if(status == 1) {
                            status = 0;
                            console.log("status的新状态：" + status);
                        }else if (status == 0) {
                            status = 1;
                            console.log("status的新状态：" + status);
                        }
                        var userLike1= {
                            "likeRemarkid":remarkId,
                            "likeUserid":userId,
                            "status":status}
                        $http({
                            method: 'PUT',
                            url: k_protocol + '/remark/like',
                            data: $.param(userLike1),
                            headers: {'Content-Type': 'application/x-www-form-urlencoded'}
                        }).then(function successCallback(res) {
                            var ob = JSON.parse(JSON.stringify(res.data));
                            $scope.msgTips(ob,remarkId);
                        }, function errorCallback(res) {
                            // layui
                            layui.use(['layer', 'form'], function(){
                                var layer = layui.layer
                                    ,form = layui.form;
                                layer.msg("点赞失败");
                            });
                            console.log(res);
                        });
                    }
                }
            },function errorCallback(res) {
                console.log(res)
            });
        }

    }

    /**
     * layui tips弹窗提示信息
     * */
    $scope.msgTips = function (ob,remarkId) {
        // layui
        layui.use(['layer', 'form'], function(){
            var layer = layui.layer
                ,form = layui.form;
            layer.tips( ob.data, '#like'+remarkId);
        });
    }


})