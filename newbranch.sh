#! /bin/sh
if [ $# -lt 2 ]
then 
	echo '请输入项目名称和日期'
	exit 0
fi
path=$1
version=dev-$2
echo '开始处理'$path
echo 分支号:$version
cd $path
git checkout master
if [ $? -ne 0 ]
then
	echo 'checkout master失败'
	exit 0
fi
git pull
if [ $? -ne 0 ]
then
	echo 'pull失败'
	exit 0
fi
git branch $version
git checkout $version
git push --set-upstream origin $version
