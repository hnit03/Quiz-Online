USE [QuizOnline]
GO
ALTER TABLE [dbo].[Subject] DROP CONSTRAINT [FK_Subject_Status]
GO
ALTER TABLE [dbo].[Subject] DROP CONSTRAINT [FK_Subject_Category]
GO
ALTER TABLE [dbo].[Registration] DROP CONSTRAINT [FK_Registration_Status]
GO
ALTER TABLE [dbo].[Registration] DROP CONSTRAINT [FK_Registration_Role]
GO
ALTER TABLE [dbo].[Question] DROP CONSTRAINT [FK_Question_Subject]
GO
ALTER TABLE [dbo].[Question] DROP CONSTRAINT [FK_Question_Status]
GO
ALTER TABLE [dbo].[History] DROP CONSTRAINT [FK_History_Subject1]
GO
ALTER TABLE [dbo].[History] DROP CONSTRAINT [FK_History_Registration]
GO
/****** Object:  Table [dbo].[Subject]    Script Date: 2/19/2021 7:44:52 AM ******/
DROP TABLE [dbo].[Subject]
GO
/****** Object:  Table [dbo].[Status]    Script Date: 2/19/2021 7:44:52 AM ******/
DROP TABLE [dbo].[Status]
GO
/****** Object:  Table [dbo].[Role]    Script Date: 2/19/2021 7:44:52 AM ******/
DROP TABLE [dbo].[Role]
GO
/****** Object:  Table [dbo].[Registration]    Script Date: 2/19/2021 7:44:52 AM ******/
DROP TABLE [dbo].[Registration]
GO
/****** Object:  Table [dbo].[Question]    Script Date: 2/19/2021 7:44:52 AM ******/
DROP TABLE [dbo].[Question]
GO
/****** Object:  Table [dbo].[History]    Script Date: 2/19/2021 7:44:52 AM ******/
DROP TABLE [dbo].[History]
GO
/****** Object:  Table [dbo].[Category]    Script Date: 2/19/2021 7:44:52 AM ******/
DROP TABLE [dbo].[Category]
GO
USE [master]
GO
/****** Object:  Database [QuizOnline]    Script Date: 2/19/2021 7:44:52 AM ******/
DROP DATABASE [QuizOnline]
GO
/****** Object:  Database [QuizOnline]    Script Date: 2/19/2021 7:44:52 AM ******/
CREATE DATABASE [QuizOnline]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'QuizOnline', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.MSSQLSERVER\MSSQL\DATA\QuizOnline.mdf' , SIZE = 3072KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'QuizOnline_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.MSSQLSERVER\MSSQL\DATA\QuizOnline_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [QuizOnline] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [QuizOnline].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [QuizOnline] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [QuizOnline] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [QuizOnline] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [QuizOnline] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [QuizOnline] SET ARITHABORT OFF 
GO
ALTER DATABASE [QuizOnline] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [QuizOnline] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [QuizOnline] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [QuizOnline] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [QuizOnline] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [QuizOnline] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [QuizOnline] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [QuizOnline] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [QuizOnline] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [QuizOnline] SET  DISABLE_BROKER 
GO
ALTER DATABASE [QuizOnline] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [QuizOnline] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [QuizOnline] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [QuizOnline] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [QuizOnline] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [QuizOnline] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [QuizOnline] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [QuizOnline] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [QuizOnline] SET  MULTI_USER 
GO
ALTER DATABASE [QuizOnline] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [QuizOnline] SET DB_CHAINING OFF 
GO
ALTER DATABASE [QuizOnline] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [QuizOnline] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [QuizOnline] SET DELAYED_DURABILITY = DISABLED 
GO
USE [QuizOnline]
GO
/****** Object:  Table [dbo].[Category]    Script Date: 2/19/2021 7:44:52 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Category](
	[categoryID] [nvarchar](50) NOT NULL,
	[categoryName] [nvarchar](50) NULL,
 CONSTRAINT [PK_Category] PRIMARY KEY CLUSTERED 
(
	[categoryID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[History]    Script Date: 2/19/2021 7:44:52 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[History](
	[historyID] [nvarchar](50) NOT NULL,
	[email] [nvarchar](50) NULL,
	[subjectID] [nvarchar](50) NULL,
	[numOfCorrect] [int] NULL,
	[total] [float] NULL,
	[createDate] [nvarchar](50) NULL,
 CONSTRAINT [PK_History] PRIMARY KEY CLUSTERED 
(
	[historyID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Question]    Script Date: 2/19/2021 7:44:52 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Question](
	[questionID] [nvarchar](50) NOT NULL,
	[questionContent] [nvarchar](250) NULL,
	[answer1] [nvarchar](250) NULL,
	[answer2] [nvarchar](250) NULL,
	[answer3] [nvarchar](250) NULL,
	[answer4] [nvarchar](250) NULL,
	[answerCorrect] [nvarchar](250) NULL,
	[createDate] [nvarchar](50) NULL,
	[subjectID] [nvarchar](50) NULL,
	[statusID] [int] NULL,
 CONSTRAINT [PK_Question] PRIMARY KEY CLUSTERED 
(
	[questionID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Registration]    Script Date: 2/19/2021 7:44:52 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Registration](
	[email] [nvarchar](50) NOT NULL,
	[password] [nvarchar](100) NULL,
	[fullname] [nvarchar](50) NULL,
	[roleID] [int] NULL,
	[statusID] [int] NULL,
 CONSTRAINT [PK_Registration] PRIMARY KEY CLUSTERED 
(
	[email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Role]    Script Date: 2/19/2021 7:44:52 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Role](
	[roleID] [int] NOT NULL,
	[roleName] [nvarchar](50) NULL,
 CONSTRAINT [PK_Role] PRIMARY KEY CLUSTERED 
(
	[roleID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Status]    Script Date: 2/19/2021 7:44:52 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Status](
	[statusID] [int] NOT NULL,
	[statusName] [nvarchar](50) NULL,
 CONSTRAINT [PK_Status] PRIMARY KEY CLUSTERED 
(
	[statusID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Subject]    Script Date: 2/19/2021 7:44:52 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Subject](
	[subjectID] [nvarchar](50) NOT NULL,
	[subjectName] [nvarchar](50) NULL,
	[time] [nvarchar](50) NULL,
	[statusID] [int] NULL,
	[categoryID] [nvarchar](50) NULL,
 CONSTRAINT [PK_Subject] PRIMARY KEY CLUSTERED 
(
	[subjectID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
INSERT [dbo].[Category] ([categoryID], [categoryName]) VALUES (N'1', N'Web')
INSERT [dbo].[Category] ([categoryID], [categoryName]) VALUES (N'2', N'Java')
INSERT [dbo].[History] ([historyID], [email], [subjectID], [numOfCorrect], [total], [createDate]) VALUES (N'31984651-18CD-4865-94AE-53E1AA85C36B', N'nhinh@fpt.vn', N'D5DC8E70-4062-4354-AA52-B839A18E4E62', 2, 10, N'2021-02-18 09:18:07')
INSERT [dbo].[History] ([historyID], [email], [subjectID], [numOfCorrect], [total], [createDate]) VALUES (N'455E458A-8A67-48E5-9B6A-6F4A19762830', N'nhinh@fpt.vn', N'8F0A6D52-C398-49F3-BDB2-15FC04182083', 2, 4, N'2021-02-18 09:19:47')
INSERT [dbo].[History] ([historyID], [email], [subjectID], [numOfCorrect], [total], [createDate]) VALUES (N'65F35FB7-3D42-4DE4-9CBA-9F4AA6D0BF7B', N'nhinh@fpt.vn', N'8F0A6D52-C398-49F3-BDB2-15FC04182083', 4, 8, N'2021-02-18 09:19:19')
INSERT [dbo].[History] ([historyID], [email], [subjectID], [numOfCorrect], [total], [createDate]) VALUES (N'7B62B26B-F34F-4B38-967E-C394C3979EC8', N'nhinh@fpt.vn', N'D5DC8E70-4062-4354-AA52-B839A18E4E62', 2, 10, N'2021-02-18 09:19:29')
INSERT [dbo].[History] ([historyID], [email], [subjectID], [numOfCorrect], [total], [createDate]) VALUES (N'7D164557-9845-4637-97E0-496C9B13E137', N'nhinh@fpt.vn', N'8F0A6D52-C398-49F3-BDB2-15FC04182083', 5, 10, N'2021-02-18 09:18:58')
INSERT [dbo].[History] ([historyID], [email], [subjectID], [numOfCorrect], [total], [createDate]) VALUES (N'8BC31BC1-1BFA-4956-B17E-02306CE5E9E6', N'nh@dc', N'23ECE5A9-7262-4666-AD09-2207447DB890', 0, 0, N'2021-02-18 01:55:16')
INSERT [dbo].[History] ([historyID], [email], [subjectID], [numOfCorrect], [total], [createDate]) VALUES (N'A0BFEE9E-EC29-4985-9C4C-3DFAE172D4EA', N'nhinh@fpt.vn', N'D5DC8E70-4062-4354-AA52-B839A18E4E62', 2, 10, N'2021-02-18 09:19:38')
INSERT [dbo].[Question] ([questionID], [questionContent], [answer1], [answer2], [answer3], [answer4], [answerCorrect], [createDate], [subjectID], [statusID]) VALUES (N'1', N'2', N'1', N'2', N'3', N'2', N'1', N'2021-1-1', N'8F0A6D52-C398-49F3-BDB2-15FC04182083', 0)
INSERT [dbo].[Question] ([questionID], [questionContent], [answer1], [answer2], [answer3], [answer4], [answerCorrect], [createDate], [subjectID], [statusID]) VALUES (N'1AD2DF46-8165-495F-A719-56A8B972FA32', N'Which of following protocol is HTTP built upon?', N'explicitly internal', N'UDP', N'2', N'None of the above', N'implicitly static', N'2021-02-18', N'23ECE5A9-7262-4666-AD09-2207447DB890', 0)
INSERT [dbo].[Question] ([questionID], [questionContent], [answer1], [answer2], [answer3], [answer4], [answerCorrect], [createDate], [subjectID], [statusID]) VALUES (N'2', N'3', N'1', N'2', N'3', N'4', N'2', N'2021-1-1', N'8F0A6D52-C398-49F3-BDB2-15FC04182083', 0)
INSERT [dbo].[Question] ([questionID], [questionContent], [answer1], [answer2], [answer3], [answer4], [answerCorrect], [createDate], [subjectID], [statusID]) VALUES (N'3', N'What was the concern of the telephone system that motivated the ARPANET design?', N'Scalability', N'Vulnerability', N'Efficiency', N'None of the above', N'Vulnerability', N'2021-1-1', N'8F0A6D52-C398-49F3-BDB2-15FC04182083', 0)
INSERT [dbo].[Question] ([questionID], [questionContent], [answer1], [answer2], [answer3], [answer4], [answerCorrect], [createDate], [subjectID], [statusID]) VALUES (N'68E9E257-7245-4E90-9A2C-651665AC5466', N' Unlike const however, read-only fields are NOT', N'explicitly internal', N' explicitly static', N'implicitly static', N'implicitly internal', N'implicitly static', N'2021-02-17', N'D5DC8E70-4062-4354-AA52-B839A18E4E62', 0)
INSERT [dbo].[Question] ([questionID], [questionContent], [answer1], [answer2], [answer3], [answer4], [answerCorrect], [createDate], [subjectID], [statusID]) VALUES (N'75BBEA1B-5A3C-4026-AA4C-09B5618DB976', N'Which of following protocol is HTTP built upon?', N'SMTP', N'UDP', N'TCP', N'IP', N'TCP', N'2021-02-06', N'8F0A6D52-C398-49F3-BDB2-15FC04182083', 0)
INSERT [dbo].[Question] ([questionID], [questionContent], [answer1], [answer2], [answer3], [answer4], [answerCorrect], [createDate], [subjectID], [statusID]) VALUES (N'B7E92C1A-51E4-4203-985C-A2EE4D07C968', N'Which of following protocol is HTTP built upon?', N'SMTP', N'UDP', N'TCP', N'IP', N'TCP', N'2021-02-06', N'8F0A6D52-C398-49F3-BDB2-15FC04182083', 0)
INSERT [dbo].[Question] ([questionID], [questionContent], [answer1], [answer2], [answer3], [answer4], [answerCorrect], [createDate], [subjectID], [statusID]) VALUES (N'F3C5931A-7CD5-4993-BF8A-6EB90D2CC9C8', N'Which of following protocol is HTTP built upon?', N'explicitly internal', N'UDP', N'TCP', N'None of the above', N'TCP', N'2021-02-17', N'D5DC8E70-4062-4354-AA52-B839A18E4E62', 0)
INSERT [dbo].[Registration] ([email], [password], [fullname], [roleID], [statusID]) VALUES (N'hoangnhinguyen33@gmail.com', N'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', N'Hoang Nhi', 0, 1)
INSERT [dbo].[Registration] ([email], [password], [fullname], [roleID], [statusID]) VALUES (N'nh@dc', N'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', N'q', 1, 1)
INSERT [dbo].[Registration] ([email], [password], [fullname], [roleID], [statusID]) VALUES (N'nhinh@fpt.vn', N'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', N'Nhi', 1, 1)
INSERT [dbo].[Registration] ([email], [password], [fullname], [roleID], [statusID]) VALUES (N'tttt@c', N'18ac3e7343f016890c510e93f935261169d9e3f565436429830faf0934f4f8e4', N'd', 1, 1)
INSERT [dbo].[Role] ([roleID], [roleName]) VALUES (0, N'Admin')
INSERT [dbo].[Role] ([roleID], [roleName]) VALUES (1, N'Student')
INSERT [dbo].[Status] ([statusID], [statusName]) VALUES (0, N'Active')
INSERT [dbo].[Status] ([statusID], [statusName]) VALUES (1, N'Deactive')
INSERT [dbo].[Subject] ([subjectID], [subjectName], [time], [statusID], [categoryID]) VALUES (N'0', N'P', N'00:00', 0, N'1')
INSERT [dbo].[Subject] ([subjectID], [subjectName], [time], [statusID], [categoryID]) VALUES (N'1', N'F', N'01:00', 1, N'1')
INSERT [dbo].[Subject] ([subjectID], [subjectName], [time], [statusID], [categoryID]) VALUES (N'18F849CB-8E57-46C0-81B1-EC55C4F35C2C', N'PRJ222', N'1:75', 0, N'1')
INSERT [dbo].[Subject] ([subjectID], [subjectName], [time], [statusID], [categoryID]) VALUES (N'21C725F2-EC18-4654-97DD-9EA7C79B8880', N'PRJ223', N'1:75', 0, N'1')
INSERT [dbo].[Subject] ([subjectID], [subjectName], [time], [statusID], [categoryID]) VALUES (N'23ECE5A9-7262-4666-AD09-2207447DB890', N'ggg', N'0:60', 0, N'1')
INSERT [dbo].[Subject] ([subjectID], [subjectName], [time], [statusID], [categoryID]) VALUES (N'8C4022EA-6934-4EAA-9A61-43735E3C7A84', N'PRJ222', N'1:75', 0, N'1')
INSERT [dbo].[Subject] ([subjectID], [subjectName], [time], [statusID], [categoryID]) VALUES (N'8F0A6D52-C398-49F3-BDB2-15FC04182083', N'PRJ321', N'03:03', 0, N'2')
INSERT [dbo].[Subject] ([subjectID], [subjectName], [time], [statusID], [categoryID]) VALUES (N'D5DC8E70-4062-4354-AA52-B839A18E4E62', N'PRN292', N'1:12', 0, N'2')
INSERT [dbo].[Subject] ([subjectID], [subjectName], [time], [statusID], [categoryID]) VALUES (N'E7BA0641-C390-4119-90C8-4AB731626A1F', N'PRJ212', N'1:75', 0, N'1')
INSERT [dbo].[Subject] ([subjectID], [subjectName], [time], [statusID], [categoryID]) VALUES (N'EE1BE515-DB51-4393-B9E1-BBD9A33731AF', N'PRJ321', N'1:00', 0, N'1')
ALTER TABLE [dbo].[History]  WITH CHECK ADD  CONSTRAINT [FK_History_Registration] FOREIGN KEY([email])
REFERENCES [dbo].[Registration] ([email])
GO
ALTER TABLE [dbo].[History] CHECK CONSTRAINT [FK_History_Registration]
GO
ALTER TABLE [dbo].[History]  WITH CHECK ADD  CONSTRAINT [FK_History_Subject1] FOREIGN KEY([subjectID])
REFERENCES [dbo].[Subject] ([subjectID])
GO
ALTER TABLE [dbo].[History] CHECK CONSTRAINT [FK_History_Subject1]
GO
ALTER TABLE [dbo].[Question]  WITH CHECK ADD  CONSTRAINT [FK_Question_Status] FOREIGN KEY([statusID])
REFERENCES [dbo].[Status] ([statusID])
GO
ALTER TABLE [dbo].[Question] CHECK CONSTRAINT [FK_Question_Status]
GO
ALTER TABLE [dbo].[Question]  WITH CHECK ADD  CONSTRAINT [FK_Question_Subject] FOREIGN KEY([subjectID])
REFERENCES [dbo].[Subject] ([subjectID])
GO
ALTER TABLE [dbo].[Question] CHECK CONSTRAINT [FK_Question_Subject]
GO
ALTER TABLE [dbo].[Registration]  WITH CHECK ADD  CONSTRAINT [FK_Registration_Role] FOREIGN KEY([roleID])
REFERENCES [dbo].[Role] ([roleID])
GO
ALTER TABLE [dbo].[Registration] CHECK CONSTRAINT [FK_Registration_Role]
GO
ALTER TABLE [dbo].[Registration]  WITH CHECK ADD  CONSTRAINT [FK_Registration_Status] FOREIGN KEY([statusID])
REFERENCES [dbo].[Status] ([statusID])
GO
ALTER TABLE [dbo].[Registration] CHECK CONSTRAINT [FK_Registration_Status]
GO
ALTER TABLE [dbo].[Subject]  WITH CHECK ADD  CONSTRAINT [FK_Subject_Category] FOREIGN KEY([categoryID])
REFERENCES [dbo].[Category] ([categoryID])
GO
ALTER TABLE [dbo].[Subject] CHECK CONSTRAINT [FK_Subject_Category]
GO
ALTER TABLE [dbo].[Subject]  WITH CHECK ADD  CONSTRAINT [FK_Subject_Status] FOREIGN KEY([statusID])
REFERENCES [dbo].[Status] ([statusID])
GO
ALTER TABLE [dbo].[Subject] CHECK CONSTRAINT [FK_Subject_Status]
GO
USE [master]
GO
ALTER DATABASE [QuizOnline] SET  READ_WRITE 
GO
