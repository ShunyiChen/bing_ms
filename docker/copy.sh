#!/bin/sh

# 复制项目的文件到对应docker路径，便于一键生成镜像。
usage() {
	echo "Usage: sh copy.sh"
	exit 1
}


# copy sql
# echo "begin copy sql "
# cp ../sql/ry_20231130.sql ./mysql/db
# cp ../sql/ry_config_20231204.sql ./mysql/db

# copy filing-ui
echo "begin filing-ui "
cp -r ./docker/filing-ui/temp/** ./docker/filing-ui/html/dist

# copy legaldb-ui
echo "begin legaldb-ui "
cp -r ./docker/legaldb-ui/temp/** ./docker/legaldb-ui/html/dist

# copy sysmgmt-ui
echo "begin sysmgmt-ui "
cp -r ./docker/sysmgmt-ui/temp/** ./docker/sysmgmt-ui/html/dist

# copy portal-ui
echo "begin portal-ui "
cp -r ./docker/portal-ui/temp/** ./docker/portal-ui/html/dist

# copy jar
echo "begin copy cbs-gateway "
cp ./cbs-gateway/target/cbs-gateway.jar ./docker/cbs/gateway/jar

echo "begin copy cbs-auth "
cp ./cbs-auth/target/cbs-auth.jar ./docker/cbs/auth/jar

echo "begin copy cbs-visual "
cp ./cbs-visual/cbs-monitor/target/cbs-visual-monitor.jar  ./docker/cbs/visual/monitor/jar

echo "begin copy cbs-modules-system "
cp ./cbs-modules/cbs-system/target/cbs-modules-system.jar ./docker/cbs/modules/system/jar

echo "begin copy cbs-modules-filingsystem "
cp ./cbs-modules/cbs-filingsystem/target/cbs-modules-filingsystem.jar ./docker/cbs/modules/filingsystem/jar

echo "begin copy cbs-modules-legaldatabase "
cp ./cbs-modules/cbs-legaldatabase/target/cbs-modules-legaldatabase.jar ./docker/cbs/modules/legaldatabase/jar

echo "begin copy cbs-modules-file "
cp ./cbs-modules/cbs-file/target/cbs-modules-file.jar ./docker/cbs/modules/file/jar

echo "begin copy cbs-modules-job "
cp ./cbs-modules/cbs-job/target/cbs-modules-job.jar ./docker/cbs/modules/job/jar

echo "begin copy cbs-modules-gen "
cp ./cbs-modules/cbs-gen/target/cbs-modules-gen.jar ./docker/cbs/modules/gen/jar

