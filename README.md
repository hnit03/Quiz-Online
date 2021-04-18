[![Practice CI with GitHub Actions using SonarCloud | © 2021 by hnit03](https://github.com/hnit03/Quiz-Online/actions/workflows/build.yml/badge.svg)](https://github.com/hnit03/Quiz-Online/actions/workflows/build.yml)

# Quiz-Online

## Background
Build a website to take the quiz.

## Program	Specifications
Build a website that takes the multi-choice quiz. Each question has 4 answers, including 1 correct answer. 
The system is basically described as follows: there are many subjects in the system, corresponding to each subject, the
number of questions and the time to take the quiz may vary. There is also question management for academic staff.  
You must use Filter as Controller and follow MVC2 model.  
Example: There are 2 subjects in the system:  
- Prj311- Java Desktop: there are 40 questions in each quiz and it takes 60 minutes for the exam.  
- Prj321- Java Web: each quiz has 50 questions and the quiz time is 80 minutes.  

## Features:  
This system contains the following functions:  
- Function	1:	Registration	- Create	an	account	to	take	the	quiz (50	points)  
o Registration	a	new	account:	email,	name,	password,	role,	status(	email as	ID).  
o The	default	role	of	new	account	is	student.  
o The	default	status	of	new	account	is	New.  
o Password	must	be	encrypted using	SHA-256	before	store	in	database.  

- Function	2:	Login- Used	to	authenticate	users	who	access	the	system	is	valid	(50 Points)  
o The	actor	enters	ID	and	password,	the	function	checks	if	the	ID	with	the	password	is	in	the	available	
account	list,	then	grant	the	access	permission.	If	not,	a	message	would	appear	no	notify	that	account	is	
not	found.  
o Login	function	includes	logout	and	welcome	functions.  

- Function	3:	Search	question	- 50	Points  
o Only	Admin	role	has	permission	to	do	this	function.  
o Admin	can	find	the	question	based on	question	name,	status	of	question	or	subject.  
o List	first	20	available	items	in	the	system	order	by	item	name.	Paging	is	required.  
o The	interface	must	show	a	list	of	questions	for	each	subject.	For	each	question,	the	answers	and	the	
correct	answer	must	be	displayed.  

- Function	4:	Create	Question- 50	points:
o Only	Admin	role	has	permission	to	do	this	function.  
o Each	question	has	an id,	question_content,	answer_content,	answer_correct,	createDate,	subjectID,
status…  
o After	do	the	action	then	the	screen	must	refresh. 

- Function	5:	Update	Question- 50	points   
o Only	Admin	role	has	permission	to	do	this	function.  
o User	can	update	the	information	of	question:	update	question_content,	answer_content,	
answer_correct,	subject,	…  
o After	do	the	action	then	the	screen	must	refresh.  

- Function	6:	Delete	Question- 50	points  
o Only	Admin	role	has	permission	to	do	this	function.  
o User	can	delete	the	question	(change	its	status	to	deActive).  
o After	do	the	action	then	the	screen	must	refresh.  

- Function	7:	Take	a quiz- 200	points  
o After	login	and	select	the	subject	that	you	want	to	take	a	quiz	then	the	quiz	will	start	and	start	the	
counter	on	screen.  
o When	starting	the	quiz,	the	system	will	randomly	select	n	questions	from	the	question	bank. N	is	a	
number	of	question	of	the	quiz.  
o The	screen	will	show	the	question	and	the	corresponding	answers.  
o The	user	selects	the	answer	and	presses	the	Next	button	to	go	to	the	next	question.  
o When	the	user	presses	the	Finish	button	or	the	timer	expires,	the	results	will	show	includes:	number	of	
correct	and	point. Then	the	quiz	detail	and	a	result	information	will	be	saved	to	the	database.  
o This	function	is	used	for	student	role	only.  

- Function	8:	Show	history- 100	points  
o User	can	take	over	the	quiz	history.  
o Support	search	function:	search	by	category,	subject	name (paging	is	required)  

## Connect me via
1. [Facebook](https://www.facebook.com/heathcliff2k)
2. [Email](mailto:hoangnhinguyen33@gmail.com)

#### © 2021 by hnit03
