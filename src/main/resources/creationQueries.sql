CREATE TABLE Users (
  UserId int IDENTITY(1,1) PRIMARY KEY,
  LastName varchar(255) NOT NULL,
  FirstName varchar(255),
  UserName varchar(20) NOT NULL UNIQUE,
  Password varchar(255) NOT NULL,
  Age int,
  Bio varchar(1000)
);

CREATE TABLE Posts (
  PostId int IDENTITY(1,1) PRIMARY KEY,
  Post VARCHAR(1000) NOT NULL,
  UserId int FOREIGN KEY REFERENCES Users(UserId),
  CreationTime DATETIME NOT NULL
);

CREATE TABLE Friends (
  UserId int FOREIGN KEY REFERENCES Users(UserId),
  FollowersId int FOREIGN KEY REFERENCES Users(UserId),
  PRIMARY KEY(UserId, FollowersId)
);