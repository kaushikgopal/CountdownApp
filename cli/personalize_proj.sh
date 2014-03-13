#!/bin/sh

if [ -z $2 ]
then
    echo "Usage: script/setup <application name> <main module package name>"
    echo "example: ./cli/personalize_proj_working.sh CountdownApp com.cmuse13.countdownapp.countdownmodule"
    echo "<application name> is the display name of your application"
    echo "<main module package name> is the fully qualified package path to the main module of your application"
    exit 0
fi

echo "Creating project to $1 using package $2"

ORIGINAL_APP_DIRECTORY="MyStarterApp"
ORIGINAL_PACKAGE_NAME="co.kaush.mystarterapp.myappmodule"
ORIGINAL_PACKAGE_PATH=`echo $ORIGINAL_PACKAGE_NAME | sed 's/\./\//g'`

NEW_APP_DIRECTORY=$1    # CountdownApp
NEW_PACKAGE_NAME=$2     # com.cmuse13.countdownapp.countdownmodule
NEW_APP_MAIN_MODULE="${NEW_APP_DIRECTORY}Module"
NEW_PACKAGE_PATH=`echo $NEW_PACKAGE_NAME | sed 's/\./\//g'`

echo "Copying files..."

mkdir -p ../$NEW_APP_DIRECTORY
cp -r * ../$NEW_APP_DIRECTORY
cp -r .idea ../$NEW_APP_DIRECTORY
cp .gitignore ../$NEW_APP_DIRECTORY

cd ../$NEW_APP_DIRECTORY
rm -f .idea/workspace.xml
rm -f .idea/tasks.xml

echo "Renaming files and directories..."

mv MyAppModule ${NEW_APP_MAIN_MODULE}
mv ${ORIGINAL_APP_DIRECTORY}.iml ${NEW_APP_DIRECTORY}.iml
rm -Rf ./${NEW_APP_MAIN_MODULE}/build ./${NEW_APP_MAIN_MODULE}/target

echo "Renaming files and directories within subModules"

mv subModules/MyAppPojos subModules/${NEW_APP_DIRECTORY}Pojos

cd subModules/${NEW_APP_DIRECTORY}Pojos

mv MyAppPojos.iml ${NEW_APP_DIRECTORY}Pojos.iml
mkdir -p src/main/java/$NEW_PACKAGE_PATH
mkdir -p src/test/java/$NEW_PACKAGE_PATH
mv src/main/java/$ORIGINAL_PACKAGE_PATH/* src/main/java/$NEW_PACKAGE_PATH
mv src/test/java/$ORIGINAL_PACKAGE_PATH/* src/test/java/$NEW_PACKAGE_PATH

# Delete directories if they are empty, i.e. if the new package name is not com.pivotallabs...
rmdir src/main/java/co/kaush/mystarterapp/myappmodule > /dev/null 2>&1
rmdir src/main/java/co/kaush/mystarterapp > /dev/null 2>&1
rmdir src/main/java/co/kaush > /dev/null 2>&1
rmdir src/main/java/co > /dev/null 2>&1
rmdir src/test/java/co/kaush/mystarterapp/myappmodule > /dev/null 2>&1
rmdir src/test/java/co/kaush/mystarterapp > /dev/null 2>&1
rmdir src/test/java/co/kaush > /dev/null 2>&1
rmdir src/test/java/co > /dev/null 2>&1

cd ../..

echo "Renaming files and directories within the Main App Module"

cd $NEW_APP_MAIN_MODULE
mv MyAppModule.iml ${NEW_APP_MAIN_MODULE}.iml
mkdir -p src/main/java/$NEW_PACKAGE_PATH
mkdir -p src/androidTest/java/$NEW_PACKAGE_PATH
mv src/main/java/$ORIGINAL_PACKAGE_PATH/* src/main/java/$NEW_PACKAGE_PATH
mv src/androidTest/java/$ORIGINAL_PACKAGE_PATH/* src/androidTest/java/$NEW_PACKAGE_PATH
rm -rf src/main/java/$ORIGINAL_PACKAGE_PATH

# Delete directories if they are empty, i.e. if the new package name is not com.pivotallabs...
rmdir src/main/java/co/kaush/mystarterapp/myappmodule > /dev/null 2>&1
rmdir src/main/java/co/kaush/mystarterapp > /dev/null 2>&1
rmdir src/main/java/co/kaush > /dev/null 2>&1
rmdir src/main/java/co > /dev/null 2>&1
rmdir src/androidTest/java/co/kaush/mystarterapp/myappmodule > /dev/null 2>&1
rmdir src/androidTest/java/co/kaush/mystarterapp > /dev/null 2>&1
rmdir src/androidTest/java/co/kaush > /dev/null 2>&1
rmdir src/androidTest/java/co > /dev/null 2>&1

cd ..

echo "Renaming in files..."

find ${NEW_APP_MAIN_MODULE} -name *.xml -print | xargs sed -i "" -e "s/MyStarterApp/$NEW_APP_DIRECTORY/g"

find . -name "*.iml" -print | xargs sed -i "" -e "s/MyStarterApp/$NEW_APP_DIRECTORY/g"
find . -name "*.iml" -print | xargs sed -i "" -e "s/MyAppModule/${NEW_APP_MAIN_MODULE}/g"
find . -name "*.iml" -print | xargs sed -i "" -e "s/MyAppPojos/${NEW_APP_DIRECTORY}Pojos/g"

find .idea -name .name -print | xargs sed -i "" -e "s/MyStarterApp/$NEW_APP_DIRECTORY/g"

find .idea -name *.xml -print | xargs sed -i "" -e "s/MyStarterApp/$NEW_APP_DIRECTORY/g"
find .idea -name *.xml -print | xargs sed -i "" -e "s/MyAppModule/${NEW_APP_MAIN_MODULE}/g"
find .idea -name *.xml -print | xargs sed -i "" -e "s/MyAppPojos/${NEW_APP_DIRECTORY}Pojos/g"

find . -name "*.gradle" -print | xargs sed -i "" -e "s/MyAppModule/${NEW_APP_MAIN_MODULE}/g"
find . -name "*.gradle" -print | xargs sed -i "" -e "s/MyAppPojos/${NEW_APP_DIRECTORY}Pojos/g"

find ${NEW_APP_MAIN_MODULE}/src -name *.java -print | xargs sed -i "" -e "s/$ORIGINAL_PACKAGE_NAME/$NEW_PACKAGE_NAME/g"
find subModules/${NEW_APP_DIRECTORY}Pojos/src -name *.java -print | xargs sed -i "" -e "s/$ORIGINAL_PACKAGE_NAME/$NEW_PACKAGE_NAME/g"

sed -i "" -e "s/$ORIGINAL_PACKAGE_NAME/$NEW_PACKAGE_NAME/g" ${NEW_APP_MAIN_MODULE}/src/main/AndroidManifest.xml
sed -i "" -e "s/$ORIGINAL_APP_DIRECTORY/$NEW_APP_DIRECTORY/g" ${NEW_APP_DIRECTORY}.iml .idea/*.xml .idea/runConfigurations/*.xml