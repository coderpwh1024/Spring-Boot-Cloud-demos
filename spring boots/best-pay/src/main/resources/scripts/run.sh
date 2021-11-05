#!/bin/bash

SERVICE_NAME=best-pay-demo
JAR_NAME=$SERVICE_NAME\.jar
PID=$SERVICE_NAME\.pid
JAVA_OPTS=" -Duser.timezone=Asia/Shanghai -Djava.awt.headless=true -Djava.net.preferIPv4Stack=true "
JAVA_MEM_OPTS=" -server -Xmx4096m -Xms4096m -Xmn2046m"


# service start
function startService(){
   if [ ! -d logs ]; then
        mkdir logs
   fi
   chmod +x $JAR_NAME
   PIDS=`ps -ef | grep java |grep  "$SERVICE_NAME" | awk '{print $2}'`
        if [ -n "$PIDS" ]; then
            echo -e "\033[40;31m ERROR \033[0m: The $SERVICE_NAME already started!"
            echo -e "\033[40;31m PID \033[0m: $PIDS"
            exit 1
        else
		    echo -e "\033[30;33m Starting the $SERVICE_NAME ... \033[0m"
            nohup java $JAVA_OPTS $JAVA_MEM_OPTS -jar $JAR_NAME >> logs/$SERVICE_NAME.log 2>&1 &
            echo $! > $PID
			#Check whether process started successfully
			COUNT=0
			while [ $COUNT -lt 1 ]; do
				echo -e ".\c"
				sleep 2
				COUNT=`ps -f | grep java | grep "$DEPLOY_DIR" | awk '{print $2}' | wc -l`
				if [ $COUNT -gt 0 ]; then
					break
				fi
			done

			echo -e "\033[30;32m success: The $SERVICE_NAME  started! \033[0m"
			PID=`cat $PID`
			echo -e "\033[30;32m PID: $PID \033[0m"
        fi
}
# service stop
function stopService (){

 PID_FILE=$PID
	    CUR_PID=`cat $PID_FILE`
		CUR_PID=""
        if [  ! -f "$PID_FILE" ]; then
        	CUR_PID=`ps -ef |grep java | grep  "$SERVICE_NAME" | awk '{print $2}'`
            if [  ! -n "$CUR_PID" ]; then
                  echo -e "\033[30;33m$ $SERVICE_NAME process not exists ! \033[0m"
                  exit 0
            fi
            else
        	CUR_PID=`cat $PID_FILE`
        fi

        echo -e "\033[30;32m The process before stop :$CUR_PID  \033[0m"
        kill $CUR_PID
        rm -rf $PID_FILE
        echo -e "\033[30;33m$ stop $SERVICE_NAME ...  \033[0m"
        sleep 5
        P_ID=`ps -ef |grep java | grep  "$SERVICE_NAME" | awk '{print $2}'`
        if [ "$P_ID" == "" ]; then
		    # ok
            echo -e "\033[30;32m $SERVICE_NAME stop success \033[0m"
        else
		    # warning
            echo -e "\033[30;33m$ SERVICE_NAME process PID :$P_ID \033[0m"
            echo -e "\033[30;33m begin kill $SERVICE_NAME process, PID :$P_ID \033[0m"
            kill -9 $P_ID
        fi

}
case "$1" in

    start)
	     (startService)
        ;;

    stop)
	    (stopService)
        ;;

    restart)
        (stopService)
        sleep 2
        (startService)
        echo -e "\033[30;32m restart $SERVICE_NAME \033[0m"
        ;;

    *)
	## help
	echo "请输入你要执行的命令."$SERVICE_NAME: "start|stop|restart"
       # echo -e "\033[30;32m ./startup.sh start \033[0m: $SERVICE_NAME start"
       # echo -e "\033[30;32m ./startup.sh stop \033[0m: $SERVICE_NAME stop"
       # echo -e "\033[30;32m ./startup.sh restart \033[0m: $SERVICE_NAME restart"
        ;;

esac
exit 0


