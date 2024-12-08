#  Building wc
https://codingchallenges.fyi/challenges/challenge-wc#the-challenge---building-wc

## Create executable jar file
cd /cli-wc/src/main/java 
javac WCApp.java
jar cfe WCApp.jar WCApp WCApp.class

## Create a ccwc binary file
mv WCApp.jar ../../../example/build
cd ../
sudo touch ccwc 
sudo chmod +x ccwc
sudo vi ccwc 

### Update ccwc file contents 
#!/bin/bash
java -jar ./build/WCApp.jar "$@"

## export binary to PATH 
export PATH=$PATH:/path/to/cli-wc/example

## Usages 
### outputs the number of bytes in a file
>ccwc -c test.txt
342190 test.txt

### outputs the number of lines in a file
>ccwc -l test.txt
7145 test.txt

### outputs the number of words in a file
>ccwc -w test.txt
58164 test.txt

### outputs the number of characters in a file
use wc itself and compare the output to your solution
>wc -m test.txt
339292 test.txt

>ccwc -m test.txt
339292 test.txt

### support the default option - i.e. no options are provided, which is the equivalent to the -c, -l and -w options
>ccwc test.txt
7145   58164  342190 test.txt

### being able to read from standard input if no filename is specified
!!TODO 

Doesnt work as expected currently.