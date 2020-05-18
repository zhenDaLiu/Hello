<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>邮件预警</title>
</head>
<style type="text/css">
    .qmbox body,.qmbox div,.qmbox p,.qmbox span,.qmbox a{margin:0;padding:0;font-family:"Microsoft YaHei";color:#666666;font-size:14px;}
    .qmbox .bodyBG{background:#eae7de;}
    .qmbox a:link,.qmbox a:visited{text-decoration:none;}
    .qmbox a:hover{text-decoration:none;}
    .qmbox a:active{text-decoration:none;}
    .qmbox table{background:#ffffff;box-shadow:0 0 1px 1px #eeeeee;}
    .qmbox .headerTd{padding:0 20px;background:#00a0e9;}
    .qmbox .mainTd{padding:10px 20px 20px;}
    .qmbox .mainTd1{padding:0px 20px 0px;}
    .qmbox .divTitle{min-width:600px;height:50px;line-height:50px;font-size:16px;color:#ffffff;float:left;}
    .qmbox .divTY{padding: 15px 0 5px;}
    .qmbox .clearDiv{width:500px;height:110px;overflow:hidden;}
    .qmbox .contactDiv{padding-top:20px;margin-bottom: 20px;border-top:1px solid #e5e5e5;}
    .qmbox .contactDiv .pWord{font-size:12px;color:#999999;}
    .qmbox .contactDiv .divWXWord .darkBlueWord{font-size:14px;color:#3289ca;}
    .qmbox .footerTd{padding:0 20px;height:62px;background:#f4f3ef;}
    .qmbox .divWord{width:300px;height:62px;line-height:62px;font-size:12px;float:left;}
    .qmbox .divA{width:300px;height:62px;line-height:62px;float:right;text-align:right;}
    .qmbox .divA a{color:#999999;font-size:12px;text-decoration:none;margin-left:8px;}
    .qmbox .divA a:hover{text-decoration:underline;color:#00a0e9;}
    .qmbox .footerTd{padding:0 20px;height:62px;background:#f4f3ef;}
    .qmbox .divWord{width:300px;height:62px;line-height:62px;font-size:12px;float:left;}
</style>
<body>
<div id="mailContentContainer" class="qmbox" style="">
    <table cellpadding="0" cellspacing="0" width="760" align="center">
        <tbody>
        <tr>
            <td height="50" class="headerTd">
                <div class="divTitle">邮件预警</div>
                <a href="http://www.tingyun.com/" class="aLogo" rel="noopener" target="_blank"></a>
            </td>
        </tr>
        <tr>
            <td height="50" class="mainTd1">
                <div class="divTY">
                    <p class="pWord">告警项</p>
                    <p class="pWord"><span style="border-bottom: 1px dashed rgb(204, 204, 204); position: relative;line-height: 48px;">${(params.alarmName)!""}</span></p>
                </div>
                <div class="divTY">
                    <p class="pWord">告警命令</p>
                    <p class="pWord"><span style="border-bottom: 1px dashed rgb(204, 204, 204); position: relative;line-height: 48px;">${(params.alarmCommand)!""}</span></p>
                </div>
                <div class="divTY">
                    <p class="pWord">阀值</p>
                    <p class="pWord"><span style="border-bottom: 1px dashed rgb(204, 204, 204); position: relative;line-height: 48px;color:#ec6a37;">${(params.evaluatorValue)!""}</span></p>
                </div>
                <div class="divTY">
                    <p class="pWord">实际值</p>
                    <p class="pWord"><span style="border-bottom: 1px dashed rgb(204, 204, 204); position: relative;line-height: 48px;color:#3289ca;">${(params.alarmValue)!""}</span></p>
                </div>
                <div class="contactDiv">
                    <p class="pWord">若存在任何疑问可邮件发送至：<span style="border-bottom:1px dashed #ccc;z-index:1" t="7" onclick="return false;">luocc@yuntongxun.com</span></p>

                </div>
            </td>
        </tr>
        <tr>
            <td class="footerTd">
                <div class="divWord">监控平台</div>
            </td>
        </tr>
        </tbody>
    </table>
</div>
</body>
</html>