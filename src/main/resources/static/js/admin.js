var k_protocol = window.location.protocol;
/**
 * 上传电影
 */
app.controller('upMovieCtrl',function ($scope, $http) {
    console.log("进入电影提交页面")



    //收集电影信息
    $scope.movie = {};
    var img;


    /**
     * 修改电影信息
    * */
    var urlSearch = window.location.search; //获取url地址
    var uploadMovieId;
    var values = urlSearch.split("=");
    if (values[0]=='?movie') {
        uploadMovieId =  values[1]; //获取修改电影的id
        $http({
            method: 'GET',
            url: k_protocol+'/movie/' + uploadMovieId,
            headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        }).then(function successCallback(res) {
            var ob = JSON.parse(JSON.stringify(res.data));
            if(200==ob.code) {
                // 显示电影图片
                oPreview = document.getElementsByClassName('img-thumbnail')[0]; // 预览图
                oPreview.src = "http://ponlc6wcj.bkt.clouddn.com/" + ob.data.img;

                $scope.movie.img = ob.data.img;
                $scope.movie.titlecn = ob.data.titlecn;
                $scope.movie.titleen = ob.data.titleen;

                $scope.movie.type = ob.data.type.split(" ");
                console.log("$scope.movie.type0:" + $scope.movie.type);
                types = ob.data.type.split(" ");


                for (var i = 0; i < types.length; i++) {
                    console.log(types[i]);
                    switch(types[i]){
                        case "剧情":
                            $scope.checkedResulta = true;
                            break;
                        case "冒险":
                            $scope.checkedResultb = true;
                            break;
                        case "爱情":
                            $scope.checkedResultc = true;
                            break;
                        case "喜剧":
                            $scope.checkedResultd = true;
                            break;
                        case "动画":
                            $scope.checkedResulte = true;
                            break;
                        case "动作":
                            $scope.checkedResultf = true;
                            break;
                        case "战争":
                            $scope.checkedResultg = true;
                            break;
                        case "恐怖":
                            $scope.checkedResulth = true;
                            break;
                        case "悬疑":
                            $scope.checkedResulti = true;
                            break;
                        case "犯罪":
                            $scope.checkedResultj = true;
                            break;
                        case "科幻":
                            $scope.checkedResultk = true;
                            break;
                        case "传记":
                            $scope.checkedResultl = true;
                            break;
                        case "奇幻":
                            $scope.checkedResulto = true;
                            break;
                        case "家庭":
                            $scope.checkedResultp = true;
                            break;
                        default:
                            break;
                    }
                }

                $scope.movie.moviesTime = ob.data.time;
                $scope.movie.director = ob.data.director;
                $scope.movie.story = ob.data.story;
                $scope.movie.actors = ob.data.actors;
                $scope.movie.movieid = uploadMovieId;
            }
        },function errorCallback(res) {
            console.log(res);
        });

    }
    console.log("修改电影的id:" + uploadMovieId);




    $scope.uploadMovie=function () {
        //图片处理
        var fd = new FormData();
        var files = document.querySelector('input[name="files"]').files;
        for (var i=0; i<files.length; i++) {
            fd.append("img", files[i]);
        }

        //复选框类型处理
        var temp="";
        for(attr in $scope.movie.type){
            if (!angular.equals($scope.movie.type[attr], "undefined") && !angular.equals($scope.movie.type[attr], undefined) && !angular.equals($scope.movie.type[attr], " ") && !angular.equals($scope.movie.type[attr], "")){
                console.log("1");
                temp += $scope.movie.type[attr]+" ";
            }
            console.log("$scope.movie.type1:" + $scope.movie.type[attr]);
        }

        $scope.movie.type = temp.toString();
        console.log("$scope.movie.type2:" + $scope.movie.type);

        //上传图片&电影信息
        if(uploadMovieId == null) {
            console.log("id为空");
            $http({
                method: 'POST',
                url:k_protocol+'/upload/movie',
                data:fd,
                headers: {'Content-Type':undefined},
                transformRequest: angular.identity
            }).then(function successCallback(res) {
                var ob = JSON.parse(JSON.stringify(res.data));
                $scope.movie.img = ob.data;
                console.log("电影图片：" + $scope.movie.img);

                if(200==ob.code) {
                    //上传电影信息
                    $http({
                        method: 'POST',
                        url:k_protocol+'/movie',
                        data:$.param($scope.movie),
                        headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
                    }).then(function successCallback(res) {
                        var ob = JSON.parse(JSON.stringify(res.data));

                        if(200==ob.code) {
                            $scope.message = ob.data;
                            $('#admin-alert').modal()
                        }else {
                            $scope.message="电影上传失败";
                            $('#admin-alert').modal()
                        }

                    }, function errorCallback(res) {
                        $scope.message="上传失败";
                        $('#admin-alert').modal()
                        console.log(res);
                    })
                }
            },function errorCallback(res) {
                $scope.message = "图片上传失败";
                console.log(res);
            })
        // 修改图片&电影信息
        }else {
            console.log("id不为空");
            $http({
                method: 'PUT',
                url: k_protocol + '/movie',
                data: $.param($scope.movie),
                headers: {'Content-Type': 'application/x-www-form-urlencoded'}
            }).then(function successCallback(res) {
                var ob = JSON.parse(JSON.stringify(res.data));

                if (200 == ob.code) {
                    $scope.message = ob.data;
                    $('#admin-alert').modal()
                } else {
                    $scope.message = "电影上传失败";
                    $('#admin-alert').modal()
                }

            }, function errorCallback(res) {
                $scope.message = "上传失败";
                $('#admin-alert').modal()
                console.log(res);
            })
        }
    }


});

/**
 * 上传演员
 */
app.controller('upActorCtrl',function ($scope, $http) {
    //收集演员信息
    $scope.actor = {};

    $scope.uploadActor=function () {
        var fd = new FormData();
        var files = document.querySelector('input[name="files"]').files;
        for (var i=0; i<files.length; i++) {
            fd.append("img", files[i]);
        }

        //传图
        $http({
            method: 'POST',
            url:k_protocol+'/upload/actor',
            data:fd,
            headers: {'Content-Type':undefined},
            transformRequest: angular.identity
        }).then(function successCallback(res) {
            var ob = JSON.parse(JSON.stringify(res.data));
            $scope.actor.img = ob.data;
            console.log($scope.actor.img)

            //传信息
            $http({
                method: 'POST',
                url:k_protocol+'/actor',
                data:$.param($scope.actor),
                headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
            }).then(function successCallback(res) {
                var ob = JSON.parse(JSON.stringify(res.data));

                if(200==ob.code) {
                    $scope.message = ob.data;
                    $('#actor-alert').modal()
                }else {
                    $scope.message = "信息上传失败";
                    $('#actor-alert').modal()
                }
            },function errorCallback(res) {
                console.log(res)
            })
            
        },function errorCallback(res) {
            $scope.message = "图片上传失败";
            console.log(res);
        })
    }
})
