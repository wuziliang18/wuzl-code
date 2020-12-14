#! /bin/sh
if [ $# -lt 1 ]
then 
	echo '请输入项目名称'
	exit 0
fi
path=$1
echo '开始处理'$path
cd $path
git checkout master
git pull
mvn clean install -Dmaven.test.skip=true 