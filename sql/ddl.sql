-- moviereview.channels definition

CREATE TABLE `channels` (
                            `id` int NOT NULL AUTO_INCREMENT,
                            `name` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL,
                            `private` tinyint(1) DEFAULT '0',
                            `createdAt` datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
                            `updatedAt` datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
                            `WorkspaceId` int DEFAULT NULL,
                            PRIMARY KEY (`id`),
                            KEY `FK_channels_workspaces_idx` (`WorkspaceId`),
                            CONSTRAINT `FK_channels_workspaces` FOREIGN KEY (`WorkspaceId`) REFERENCES `workspace` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- moviereview.Movie definition

CREATE TABLE `Movie` (
                         `id` int NOT NULL AUTO_INCREMENT,
                         `audience` bigint DEFAULT NULL,
                         `movieCd` int NOT NULL,
                         `title` longtext COLLATE utf8mb4_unicode_ci,
                         `rank` bigint DEFAULT NULL,
                         `createdAt` datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
                         `updatedAt` datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
                         `poster` varchar(1024) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                         `vector` json DEFAULT NULL,
                         `rankInten` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                         `plot` longtext COLLATE utf8mb4_unicode_ci,
                         `rankOldAndNew` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                         `openDt` datetime(6) DEFAULT NULL,
                         `genre` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                         `director` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                         `ratting` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                         PRIMARY KEY (`id`),
                         UNIQUE KEY `Movie_movieCd_key` (`movieCd`),
                         KEY `movieId` (`movieCd`)
) ENGINE=InnoDB AUTO_INCREMENT=266 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- moviereview.MovieVod definition

CREATE TABLE `MovieVod` (
                            `id` int NOT NULL AUTO_INCREMENT,
                            `vodUrl` varchar(1024) NOT NULL,
                            `movieCd` int NOT NULL,
                            `createdAt` datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
                            `updatedAt` datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
                            `deletedAt` datetime(6) DEFAULT NULL,
                            PRIMARY KEY (`id`),
                            KEY `MovieVod_movieCd_fkey_idx` (`movieCd`),
                            CONSTRAINT `MovieVod_movieCd_fkey` FOREIGN KEY (`movieCd`) REFERENCES `Movie` (`movieCd`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=366 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- moviereview.Recruit definition

CREATE TABLE `Recruit` (
                           `id` int NOT NULL AUTO_INCREMENT,
                           `userno` int NOT NULL,
                           `title` varchar(256) NOT NULL,
                           `content` varchar(1024) NOT NULL,
                           `screening_date` datetime(6) DEFAULT NULL,
                           `location` varchar(255) DEFAULT NULL,
                           `status` enum('open','matched','closed') NOT NULL DEFAULT 'open',
                           `createdAt` datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
                           `updatedAt` datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
                           `deletedAt` datetime(6) DEFAULT NULL,
                           `theater name` varchar(45) DEFAULT NULL,
                           PRIMARY KEY (`id`),
                           KEY `Recruit_userno_fkey_idx` (`userno`),
                           CONSTRAINT `Recruit_userno_fkey` FOREIGN KEY (`userno`) REFERENCES `User` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- moviereview.RecruitComment definition

CREATE TABLE `RecruitComment` (
                                  `id` int NOT NULL AUTO_INCREMENT,
                                  `recruit_id` int NOT NULL,
                                  `userno` int NOT NULL,
                                  `content` varchar(256) NOT NULL,
                                  `createdAt` datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
                                  `updatedAt` datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
                                  `deletedAt` datetime(6) DEFAULT NULL,
                                  PRIMARY KEY (`id`),
                                  KEY `RecruitComment_recruitno_fkey_idx` (`recruit_id`),
                                  KEY `RecruitComment_userno_fkey_idx` (`userno`),
                                  CONSTRAINT `RecruitComment_recruitno_fkey` FOREIGN KEY (`recruit_id`) REFERENCES `Recruit` (`id`),
                                  CONSTRAINT `RecruitComment_userno_fkey` FOREIGN KEY (`userno`) REFERENCES `User` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
-- moviereview.RecruitMember definition

-- moviereview.`User` definition

CREATE TABLE `User` (
                        `id` int NOT NULL AUTO_INCREMENT,
                        `email` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
                        `nickname` longtext COLLATE utf8mb4_unicode_ci,
                        `password` longtext COLLATE utf8mb4_unicode_ci,
                        `createdAt` datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
                        `updatedAt` datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
                        `deletedAt` datetime(6) DEFAULT NULL,
                        `provider` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'credentials',
                        `image` longtext COLLATE utf8mb4_unicode_ci,
                        `marketing_agreed` tinyint(1) NOT NULL DEFAULT '0' COMMENT '마케팅 수신 동의 여부',
                        `gender` enum('male','female') COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                        PRIMARY KEY (`id`),
                        UNIQUE KEY `uni_User_email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


-- moviereview.article definition

CREATE TABLE `article` (
                           `id` int NOT NULL AUTO_INCREMENT,
                           `userno` int NOT NULL,
                           `title` varchar(255) NOT NULL,
                           `content` text NOT NULL,
                           `like_count` int NOT NULL DEFAULT '0',
                           `dislike_count` int NOT NULL DEFAULT '0',
                           `comment_count` int NOT NULL DEFAULT '0',
                           `createdAt` datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
                           `updatedAt` datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
                           `deletedAt` datetime(6) DEFAULT NULL,
                           PRIMARY KEY (`id`),
                           KEY `Article_userno_fkey_idx` (`userno`),
                           CONSTRAINT `Article_userno_fkey` FOREIGN KEY (`userno`) REFERENCES `User` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- moviereview.articleComments definition

CREATE TABLE `articleComments` (
                                   `id` int NOT NULL AUTO_INCREMENT,
                                   `articleId` int NOT NULL,
                                   `userno` int NOT NULL,
                                   `content` text NOT NULL,
                                   `createdAt` datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
                                   `updatedAt` datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
                                   `deletedAt` datetime(6) DEFAULT NULL,
                                   PRIMARY KEY (`id`),
                                   KEY `ArticleComments_userno_fkey_idx` (`userno`),
                                   KEY `ArticleComments_articleId_fkey_idx` (`articleId`),
                                   CONSTRAINT `ArticleComments_articleId_fkey` FOREIGN KEY (`articleId`) REFERENCES `article` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
                                   CONSTRAINT `ArticleComments_userno_fkey` FOREIGN KEY (`userno`) REFERENCES `User` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- moviereview.articleLikes definition

CREATE TABLE `articleLikes` (
                                `id` int NOT NULL AUTO_INCREMENT,
                                `article_id` int NOT NULL,
                                `userno` int NOT NULL,
                                `type` enum('like','dislike') NOT NULL,
                                `createdAt` datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
                                `updatedAt` datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
                                PRIMARY KEY (`id`),
                                UNIQUE KEY `unique_like` (`article_id`,`userno`,`type`),
                                KEY `ArticleLike_userno_fkey_idx` (`userno`),
                                KEY `ArticleLike_articleId_fkey_idx` (`article_id`),
                                CONSTRAINT `ArticleLike_articleId_fkey` FOREIGN KEY (`article_id`) REFERENCES `article` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
                                CONSTRAINT `ArticleLike_userno_fkey` FOREIGN KEY (`userno`) REFERENCES `User` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- moviereview.channelchats definition

CREATE TABLE `channelchats` (
                                `id` int NOT NULL AUTO_INCREMENT,
                                `content` text COLLATE utf8mb4_unicode_ci NOT NULL,
                                `createdAt` datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
                                `updatedAt` datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
                                `UserId` int DEFAULT NULL,
                                `ChannelId` int DEFAULT NULL,
                                PRIMARY KEY (`id`),
                                KEY `FK_8494e7d49237c46d648fbab8cf4_idx` (`UserId`),
                                KEY `FK_8494e7d49237c46d648fbab8cf4_idx1` (`ChannelId`),
                                CONSTRAINT `FK_channelchats_channles` FOREIGN KEY (`ChannelId`) REFERENCES `channels` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
                                CONSTRAINT `FK_channelchats_user` FOREIGN KEY (`UserId`) REFERENCES `User` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- moviereview.channelmembers definition

CREATE TABLE `channelmembers` (
                                  `ChannelId` int NOT NULL,
                                  `UserId` int NOT NULL,
                                  `createdAt` datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
                                  `updatedAt` datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
                                  PRIMARY KEY (`UserId`,`ChannelId`),
                                  CONSTRAINT `FK_channelmembers_user` FOREIGN KEY (`UserId`) REFERENCES `User` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- moviereview.channels definition

CREATE TABLE `channels` (
                            `id` int NOT NULL AUTO_INCREMENT,
                            `name` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL,
                            `private` tinyint(1) DEFAULT '0',
                            `createdAt` datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
                            `updatedAt` datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
                            `WorkspaceId` int DEFAULT NULL,
                            PRIMARY KEY (`id`),
                            KEY `FK_channels_workspaces_idx` (`WorkspaceId`),
                            CONSTRAINT `FK_channels_workspaces` FOREIGN KEY (`WorkspaceId`) REFERENCES `workspace` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- moviereview.chat_messages definition

CREATE TABLE `chat_messages` (
                                 `id` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
                                 `chatRoomId` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
                                 `senderId` int NOT NULL,
                                 `content` longtext COLLATE utf8mb4_unicode_ci NOT NULL,
                                 `createdAt` datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
                                 `updatedAt` datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
                                 `deletedAt` datetime(6) DEFAULT NULL,
                                 PRIMARY KEY (`id`),
                                 KEY `ChatMessage_chatRoomId_fkey_idx` (`chatRoomId`),
                                 KEY `ChatMessage_senderId_fkey_idx` (`senderId`),
                                 KEY `ChatMessage_createdAt_idx` (`createdAt`),
                                 CONSTRAINT `ChatMessage_chatRoomId_fkey` FOREIGN KEY (`chatRoomId`) REFERENCES `chat_rooms` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
                                 CONSTRAINT `ChatMessage_senderId_fkey` FOREIGN KEY (`senderId`) REFERENCES `User` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- moviereview.chat_room_members definition

CREATE TABLE `chat_room_members` (
                                     `id` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
                                     `chatRoomId` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
                                     `userId` int NOT NULL,
                                     `joinedAt` datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
                                     `leftAt` datetime(6) DEFAULT NULL,
                                     PRIMARY KEY (`id`),
                                     UNIQUE KEY `unique_chat_room_member` (`chatRoomId`,`userId`),
                                     KEY `ChatRoomMember_chatRoomId_fkey_idx` (`chatRoomId`),
                                     KEY `ChatRoomMember_userId_fkey_idx` (`userId`),
                                     CONSTRAINT `ChatRoomMember_chatRoomId_fkey` FOREIGN KEY (`chatRoomId`) REFERENCES `chat_rooms` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
                                     CONSTRAINT `ChatRoomMember_userId_fkey` FOREIGN KEY (`userId`) REFERENCES `User` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- moviereview.chat_rooms definition

CREATE TABLE `chat_rooms` (
                              `id` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
                              `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
                              `type` enum('direct','group') COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'direct',
                              `createdAt` datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
                              `updatedAt` datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
                              PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- moviereview.match_applications definition

CREATE TABLE `match_applications` (
                                      `id` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
                                      `matchPostId` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
                                      `applicantUserno` int NOT NULL,
                                      `applicantName` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
                                      `message` longtext COLLATE utf8mb4_unicode_ci NOT NULL,
                                      `status` enum('pending','accepted','rejected') COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'pending',
                                      `createdAt` datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
                                      PRIMARY KEY (`id`),
                                      UNIQUE KEY `unique_match_application` (`matchPostId`,`applicantUserno`),
                                      KEY `MatchApplication_matchPostId_fkey_idx` (`matchPostId`),
                                      KEY `MatchApplication_applicantUserno_fkey_idx` (`applicantUserno`),
                                      CONSTRAINT `MatchApplication_applicantUserno_fkey` FOREIGN KEY (`applicantUserno`) REFERENCES `User` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
                                      CONSTRAINT `MatchApplication_matchPostId_fkey` FOREIGN KEY (`matchPostId`) REFERENCES `match_posts` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- moviereview.match_posts definition

CREATE TABLE `match_posts` (
                               `id` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
                               `title` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
                               `content` longtext COLLATE utf8mb4_unicode_ci NOT NULL,
                               `movieTitle` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
                               `theaterName` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
                               `showTime` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
                               `maxParticipants` int NOT NULL,
                               `location` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
                               `userno` int NOT NULL,
                               `createdAt` datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
                               `updatedAt` datetime(6) DEFAULT NULL,
                               `deletedAt` datetime(6) DEFAULT NULL,
                               PRIMARY KEY (`id`),
                               KEY `MatchPost_userno_fkey_idx` (`userno`),
                               KEY `MatchPost_createdAt_idx` (`createdAt`),
                               CONSTRAINT `MatchPost_userno_fkey` FOREIGN KEY (`userno`) REFERENCES `User` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- moviereview.movieScore definition

CREATE TABLE `movieScore` (
                              `id` int NOT NULL AUTO_INCREMENT,
                              `movieCd` int NOT NULL,
                              `score` double DEFAULT NULL,
                              `createdAt` datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
                              `updatedAt` datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
                              `deletedAt` datetime(6) DEFAULT NULL,
                              `Userno` int NOT NULL,
                              PRIMARY KEY (`id`),
                              UNIQUE KEY `movieCd_userno_unique` (`movieCd`,`Userno`),
                              KEY `movieId_idx` (`movieCd`),
                              KEY `movieScore_Userno_fkey` (`Userno`),
                              CONSTRAINT `movieScore_movieCd_fkey` FOREIGN KEY (`movieCd`) REFERENCES `Movie` (`movieCd`) ON DELETE CASCADE ON UPDATE CASCADE,
                              CONSTRAINT `movieScore_Userno_fkey` FOREIGN KEY (`Userno`) REFERENCES `User` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
-- moviereview.workspace definition

-- moviereview.workspace definition

CREATE TABLE `workspace` (
                             `id` int NOT NULL AUTO_INCREMENT,
                             `name` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL,
                             `url` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL,
                             `createdAt` datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
                             `updatedAt` datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
                             `deletedAt` datetime(6) DEFAULT NULL,
                             `OwnerId` int DEFAULT NULL,
                             PRIMARY KEY (`id`),
                             KEY `FK_workspace_user_idx` (`OwnerId`),
                             CONSTRAINT `FK_workspace_user` FOREIGN KEY (`OwnerId`) REFERENCES `User` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- moviereview.workspacemembers definition

CREATE TABLE `workspacemembers` (
                                    `WorkspaceId` int NOT NULL,
                                    `UserId` int NOT NULL,
                                    `createdAt` datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
                                    `updatedAt` datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
                                    `loggedInAt` datetime DEFAULT NULL,
                                    PRIMARY KEY (`UserId`,`WorkspaceId`),
                                    CONSTRAINT `FK_workspacemembers_user` FOREIGN KEY (`UserId`) REFERENCES `User` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;