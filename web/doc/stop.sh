#!/bin/sh 
PID=`ps -ef|grep fast-web|grep -v grep|awk '{print$2}'`
kill -9  $PID