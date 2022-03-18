#! /bin/sh
if [ $# -lt 2 ]
then 
	echo '请输入项目名称和版本号'
	exit 0
fi
path=$1
echo '开始处理'$path
cd $path
git checkout $2
git pull


