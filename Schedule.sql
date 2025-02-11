CREATE TABLE users (
                       user_id      BIGINT AUTO_INCREMENT PRIMARY KEY,
                       user_name    VARCHAR(50)  NOT NULL,
                       user_email   VARCHAR(100) NOT NULL UNIQUE,
                       password     VARCHAR(255) NOT NULL,
                       create_date  DATETIME DEFAULT CURRENT_TIMESTAMP,
                       update_date  DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE schedule (
                          schedule_id     BIGINT AUTO_INCREMENT PRIMARY KEY,
                          schedule_title  VARCHAR(100) NOT NULL,
                          schedule_todo   VARCHAR(500) NOT NULL,
                          create_date     DATETIME DEFAULT CURRENT_TIMESTAMP,
                          update_date     DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                          user_id         BIGINT NOT NULL,
                          FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);