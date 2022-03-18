
#! /bin/sh
#在指定路径下删除targe和git

#防止攻击 把IFS还原为默认值
IFS=' 
	'
function recur_dir() {
    file=`ls $1`
    #echo $1
    cd $1
    for i in $ $file
    do
        if [ -f $i ]
        then
            echo $i
            do_rm_exe $i
        fi
        
        if [ -d $i ]
        then
            recur_dir $i
        fi
    done
    cd ..
}

#查找目录
searchPath=

#使用命令替换赋值

if [ $# -lt 1 ]
then 
	echo '请输入项目目录'
	exit 0
fi

echo searchPath=$1

echo $searchPath'开始查找...'
cd $searchPath

for dir in $searchPath


done


