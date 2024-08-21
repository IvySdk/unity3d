#!/bin/bash

#$1 图片路径
#$2 图片名称 png结尾
#$3 图片集文件夹的次级文件夹名
iconPath=$1/$2
iconName=${2%.png}

#创建图标文件夹
tmpName=$(echo ${iconName:0:1} | tr '[a-z]' '[A-Z]')${iconName:1}
tmpDir=$1/$3/App$tmpName.appiconset
if [ -d $tmpDir ]; then
    rm -rf $tmpDir
fi
mkdir $tmpDir

#复制图片
cp -f $iconPath $tmpDir

#文件夹中生成目标图标
convert $iconPath -resize 40x40 $tmpDir/${iconName}_iphone20ptx2.png
convert $iconPath -resize 60x60 $tmpDir/${iconName}_iphone20ptx3.png

convert $iconPath -resize 58x58 $tmpDir/${iconName}_iphone29ptx2.png
convert $iconPath -resize 87x87 $tmpDir/${iconName}_iphone29ptx3.png

convert $iconPath -resize 80x80 $tmpDir/${iconName}_iphone40ptx2.png
convert $iconPath -resize 120x120 $tmpDir/${iconName}_iphone40ptx3.png

convert $iconPath -resize 120x120 $tmpDir/${iconName}_iphone60ptx2.png
convert $iconPath -resize 180x180 $tmpDir/${iconName}_iphone60ptx3.png


convert $iconPath -resize 20x20 $tmpDir/${iconName}_ipad20ptx1.png
convert $iconPath -resize 40x40 $tmpDir/${iconName}_ipad20ptx2.png
convert $iconPath -resize 29x29 $tmpDir/${iconName}_ipad29ptx1.png
convert $iconPath -resize 58x58 $tmpDir/${iconName}_ipad29ptx2.png
convert $iconPath -resize 40x40 $tmpDir/${iconName}_ipad40ptx1.png
convert $iconPath -resize 80x80 $tmpDir/${iconName}_ipad40ptx2.png
convert $iconPath -resize 76x76 $tmpDir/${iconName}_ipad76ptx1.png
convert $iconPath -resize 152x152 $tmpDir/${iconName}_ipad76ptx2.png
convert $iconPath -resize 167x167 $tmpDir/${iconName}_ipad83ptx2.png
