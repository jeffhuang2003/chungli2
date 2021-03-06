USE [master]
GO
/****** Object:  Database [chungli]    Script Date: 2016/4/17 下午 07:33:22 ******/
CREATE DATABASE [chungli]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'chungli', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.SQLEXPRESS\MSSQL\DATA\chungli.mdf' , SIZE = 5120KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'chungli_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.SQLEXPRESS\MSSQL\DATA\chungli_log.ldf' , SIZE = 2048KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [chungli] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [chungli].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [chungli] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [chungli] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [chungli] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [chungli] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [chungli] SET ARITHABORT OFF 
GO
ALTER DATABASE [chungli] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [chungli] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [chungli] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [chungli] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [chungli] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [chungli] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [chungli] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [chungli] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [chungli] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [chungli] SET  DISABLE_BROKER 
GO
ALTER DATABASE [chungli] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [chungli] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [chungli] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [chungli] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [chungli] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [chungli] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [chungli] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [chungli] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [chungli] SET  MULTI_USER 
GO
ALTER DATABASE [chungli] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [chungli] SET DB_CHAINING OFF 
GO
ALTER DATABASE [chungli] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [chungli] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [chungli] SET DELAYED_DURABILITY = DISABLED 
GO
USE [chungli]
GO
/****** Object:  Table [dbo].[Brokerage]    Script Date: 2016/4/17 下午 07:33:22 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Brokerage](
	[BrokId] [int] IDENTITY(1,1) NOT NULL,
	[BrokName] [nvarchar](50) NOT NULL,
	[CrDate] [datetime] NULL,
	[CrUser] [nvarchar](20) NULL,
	[DateStamp] [datetime] NULL,
	[UserStamp] [nvarchar](20) NULL,
 CONSTRAINT [PK_Brokerage] PRIMARY KEY CLUSTERED 
(
	[BrokId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY],
UNIQUE NONCLUSTERED 
(
	[BrokName] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[EaProgram]    Script Date: 2016/4/17 下午 07:33:22 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[EaProgram](
	[EaId] [int] NOT NULL,
	[EaName] [nvarchar](50) NOT NULL,
	[EaPayAmount] [int] NOT NULL,
	[CrDate] [datetime] NULL,
	[CrUser] [nvarchar](20) NULL,
	[DateStamp] [datetime] NULL,
	[UserStamp] [nvarchar](20) NULL,
 CONSTRAINT [PK_EaProgram] PRIMARY KEY CLUSTERED 
(
	[EaId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY],
UNIQUE NONCLUSTERED 
(
	[EaName] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Role]    Script Date: 2016/4/17 下午 07:33:22 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Role](
	[RoleId] [int] NOT NULL,
	[RoleName] [nvarchar](10) NOT NULL,
	[CrDate] [datetime] NULL,
	[CrUser] [nvarchar](20) NULL,
	[DateStamp] [datetime] NULL,
	[UserStamp] [nvarchar](20) NULL,
 CONSTRAINT [PK_Role] PRIMARY KEY CLUSTERED 
(
	[RoleId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Team]    Script Date: 2016/4/17 下午 07:33:22 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Team](
	[TeamId] [int] NOT NULL,
	[TeamName] [nvarchar](50) NOT NULL,
	[CrDate] [datetime] NULL,
	[CrUser] [nvarchar](20) NULL,
	[DateStamp] [datetime] NULL,
	[UserStamp] [nvarchar](20) NULL,
 CONSTRAINT [PK_team] PRIMARY KEY CLUSTERED 
(
	[TeamId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY],
UNIQUE NONCLUSTERED 
(
	[TeamName] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[UserLive]    Script Date: 2016/4/17 下午 07:33:22 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[UserLive](
	[UserId] [nvarchar](50) NOT NULL,
	[UserLiveId] [nvarchar](10) NOT NULL,
	[EaId] [int] NOT NULL,
	[BrokId] [int] NOT NULL,
	[Count] [int] NULL DEFAULT ((0)),
	[Status] [int] NOT NULL,
	[CrDate] [datetime] NULL,
	[CrUser] [nvarchar](20) NULL,
	[DateStamp] [datetime] NULL,
	[UserStamp] [nvarchar](20) NULL,
 CONSTRAINT [PK_UserLive] PRIMARY KEY CLUSTERED 
(
	[UserId] ASC,
	[UserLiveId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[UserPay]    Script Date: 2016/4/17 下午 07:33:22 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[UserPay](
	[UserPayID] [nvarchar](20) NOT NULL,
	[UserId] [nvarchar](50) NOT NULL,
	[EaID] [int] NOT NULL,
	[EaPayAmount] [int] NOT NULL,
	[StartTime] [datetime] NOT NULL,
	[EndTime] [datetime] NOT NULL,
	[CrDate] [datetime] NULL,
	[CrUser] [nvarchar](20) NULL,
	[DateStamp] [datetime] NULL,
	[UserStamp] [nvarchar](20) NULL,
 CONSTRAINT [PK_userPay] PRIMARY KEY CLUSTERED 
(
	[UserPayID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[UserProfile]    Script Date: 2016/4/17 下午 07:33:22 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[UserProfile](
	[Email] [nvarchar](50) NOT NULL,
	[ChineseName] [nvarchar](20) NOT NULL,
	[Password] [nvarchar](20) NOT NULL,
	[Phone] [nvarchar](20) NOT NULL,
	[Team] [int] NOT NULL,
	[CrDate] [datetime] NULL,
	[CrUser] [nvarchar](20) NULL,
	[DateStamp] [datetime] NULL,
	[UserStamp] [nvarchar](20) NULL,
	[EnglishName] [nvarchar](50) NULL,
	[LeaderEmail] [nvarchar](50) NOT NULL,
	[RoleId] [int] NULL DEFAULT ((0)),
 CONSTRAINT [PK_user] PRIMARY KEY CLUSTERED 
(
	[Email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY],
UNIQUE NONCLUSTERED 
(
	[Email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
USE [master]
GO
ALTER DATABASE [chungli] SET  READ_WRITE 
GO
