**************************login wep app**********************
******app job*****
login wep app lets users register at telepin software company and let them also register other users through Bulk Registration page by uploading csv files
*******app structure*******
conatins of 4 pages :
				Entry page ( nomal Registration page )
				login page (to login to your account )
				Main page ( your account page )
				Bulk Registration page ( to register other users with csv files)
***** to use login app ***********
hit localhost:8080/login/ in your browser url tab after deploying the war file in your web appliaction server (Tomcat in that case).

********CVS file  structure*************
normal text file or .cvs file with this structure :
		USERNAME,PASSWORD,BIRTHDATE,GENDER in each line 

USERNAME : length shoud be between 3 and 15 characters and doesn't containe any spaces.

PASSWORD: length shoud be between 8 and 20 characters has 1 Uppercase letter  and 1 number and doesn't contain any spaces .

BIRTHDATE : date should be in format YYYY-MM-DD and year between 2000 and 1975 .

GENDER : should be female or male .

notes :
		any empty line in cvs file will be ignored
		wrong format inputs will be ignored 
		not ordered correct format inputs will be ignored 
		any username that's already registered in system will be ignored

application assume that ur input file type will be .cvs or .txt		
