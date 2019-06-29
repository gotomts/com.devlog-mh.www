-- アカウントテーブルの作成
CREATE TABLE role (
  id SERIAL PRIMARY KEY,    -- アカウント種類ID
  name VARCHAR(31) NOT NULL    -- 種類名
);

-- アカウント種類のデータ挿入
INSERT INTO role (name) VALUES('選択してください');
INSERT INTO role (name) VALUES('管理者');
INSERT INTO role (name) VALUES('一般ユーザー');

-- ユーザーテーブルの作成
CREATE TABLE users (
  id SERIAL PRIMARY KEY,    -- ユーザーID
  name VARCHAR(255) UNIQUE,   -- ユーザー名
  email VARCHAR(255) UNIQUE,    -- メールアドレス
  password VARCHAR(255) NOT NULL,    -- パスワード
  role_id INTEGER REFERENCES role(id),   -- アカウント種類ID
  updater_id INTEGER DEFAULT 1,  -- 更新者
  created TIMESTAMP DEFAULT current_timestamp,    -- 作成日時
  updated TIMESTAMP DEFAULT current_timestamp,     -- 更新日時
  delflg INTEGER DEFAULT 0    -- 削除フラグ
);

-- 画像テーブルの作成
CREATE TABLE images (
  id SERIAL PRIMARY KEY,    -- 画像ID
  name VARCHAR(255) NOT NULL,    -- 画像名
  url VARCHAR(255) NOT NULL,    -- 画像url
  title VARCHAR(255),    -- title属性
  alt VARCHAR(255),    -- alt属性
  updater_id INTEGER NOT NULL,  -- 更新者
  created TIMESTAMP NOT NULL DEFAULT current_timestamp,    -- 作成日時
  updated TIMESTAMP NOT NULL DEFAULT current_timestamp,     -- 更新日時
  delflg INTEGER DEFAULT 0  -- 削除フラグ  
);

-- カテゴリテーブルの作成
CREATE TABLE categories (
  id SERIAL PRIMARY KEY,    -- カテゴリーID
  name VARCHAR(63) NOT NULL,    -- カテゴリー名
  updater_id INTEGER DEFAULT 1,  -- 更新者
  created TIMESTAMP NOT NULL DEFAULT current_timestamp,    -- 作成日時
  updated TIMESTAMP NOT NULL DEFAULT current_timestamp,     -- 更新日時  
  delflg INTEGER DEFAULT 0 -- 削除フラグ
);

-- ステータステーブルの作成
CREATE TABLE statuses (
  id SERIAL PRIMARY KEY,    -- ステータスID
  name VARCHAR(31) NOT NULL    -- ステータス名
);

INSERT INTO statuses (name) VALUES('下書き');
INSERT INTO statuses (name) VALUES('公開');
INSERT INTO statuses (name) VALUES('限定公開');

-- アイキャッチ画像テーブルの作成
CREATE TABLE post_images (
  id SERIAL PRIMARY KEY,    -- 画像ID
  name VARCHAR(255),
  url VARCHAR(255),
  title VARCHAR(255),
  alt VARCHAR(255)
);

-- 投稿記事テーブルの作成
CREATE TABLE posts (
  id SERIAL PRIMARY KEY,    -- 記事ID
  url VARCHAR(255) NOT NULL UNIQUE,    -- 記事URL
  title TEXT NOT NULL,    -- タイトル
  description TEXT,    -- ディスクリプション
  keyword TEXT,    -- キーワード
  content TEXT,    -- 内容
  image_id INTEGER REFERENCES,    -- カテゴリーID
  category_id INTEGER NOT NULL REFERENCES categories(id),    -- カテゴリーID
  status_id INTEGER NOT NULL REFERENCES statuses(id),    -- ステータスID
  updater_id INTEGER DEFAULT 1,  -- 更新者
  created TIMESTAMP NOT NULL DEFAULT current_timestamp,    -- 作成日時
  updated TIMESTAMP NOT NULL DEFAULT current_timestamp,    -- 更新日時
  delflg INTEGER DEFAULT 0    -- 削除フラグ
);

-- SPRING SESSONに利用するテーブルの定義
CREATE TABLE SPRING_SESSION (
	PRIMARY_ID CHAR(36) NOT NULL,
	SESSION_ID CHAR(36) NOT NULL,
	CREATION_TIME BIGINT NOT NULL,
	LAST_ACCESS_TIME BIGINT NOT NULL,
	MAX_INACTIVE_INTERVAL INT NOT NULL,
	EXPIRY_TIME BIGINT NOT NULL,
	PRINCIPAL_NAME VARCHAR(100),
	CONSTRAINT SPRING_SESSION_PK PRIMARY KEY (PRIMARY_ID)
);

-- SPRING SESSONに利用するテーブルの定義
CREATE UNIQUE INDEX SPRING_SESSION_IX1 ON SPRING_SESSION (SESSION_ID);
CREATE INDEX SPRING_SESSION_IX2 ON SPRING_SESSION (EXPIRY_TIME);
CREATE INDEX SPRING_SESSION_IX3 ON SPRING_SESSION (PRINCIPAL_NAME);

-- SPRING SESSONに利用するテーブルの定義
CREATE TABLE SPRING_SESSION_ATTRIBUTES (
	SESSION_PRIMARY_ID CHAR(36) NOT NULL,
	ATTRIBUTE_NAME VARCHAR(200) NOT NULL,
	ATTRIBUTE_BYTES BYTEA NOT NULL,
	CONSTRAINT SPRING_SESSION_ATTRIBUTES_PK PRIMARY KEY (SESSION_PRIMARY_ID, ATTRIBUTE_NAME),
	CONSTRAINT SPRING_SESSION_ATTRIBUTES_FK FOREIGN KEY (SESSION_PRIMARY_ID) REFERENCES SPRING_SESSION(PRIMARY_ID) ON DELETE CASCADE
);
