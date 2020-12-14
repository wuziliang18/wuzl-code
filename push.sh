#! /bin/sh
if [ $# -eq 0 ]
then 
	echo '请输入项目名称'
	exit 0
fi
path=$1
echo '开始处理'$path
cd $path
git add *
git commit -m 'commit'
git push