- Given standard input with one email address per line. 
eg. 

joeblogs@hotmail.com    
andrew.smith@gmail.com     
....    

 outputs the 10 domains (or less if there aren't that many) that appear the most often with a count of the number of times it appears after each domain. 

eg.     
hotmail.com 2553     
yahoo.com 1315     

and up to 8 more lines.  

 
 
 
 **Prerequistes :** 
 
 - Java 8
 - Maven 3.x
 
 * Steps to run this project from IDE: 
    - Clone project
    - Import to your favorite IDE
    - Go to Folder and Run command ***mvn clean install -U*** to make sure all dependencies are being imported.
    - Run main Class ***TopnEmailDomainsMain***
 
 * Steps to run this project from the command Line using JAR file: 
    
    - Clone project
    - go inside the folder : ***cd top-n-domain-emails***
    - Run command ***mvn clean install -U***
    - Run command ***java -jar target/chalenges-1.0-SNAPSHOT.jar*** 
    
 **Explanation :**
  
  - I generate a CSV file containing random emails with random domain providers.
  - Then I read the file, and then I count the frequency of each domain name.   
     - I have used for that Java 8 Stream, Group by and Count.    
     - Other ways are possible like using java.util.Collections.frequency()   
     - Another way is to count "manually" the numbers of each occurrences and sort them later one by one.   
    

