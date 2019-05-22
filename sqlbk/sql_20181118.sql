-- アカウントテーブルの作成
CREATE TABLE role (
  id SERIAL PRIMARY KEY,    -- アカウント種類ID
  name VARCHAR(31) NOT NULL    -- 種類名
);

-- アカウント種類のデータ挿入
INSERT INTO role (name) VALUES('管理者');
INSERT INTO role (name) VALUES('一般ユーザー');

-- ユーザーテーブルの作成
CREATE TABLE users (
  id SERIAL PRIMARY KEY,    -- ユーザーID
  name VARCHAR(255) UNIQUE,   -- ユーザー名
  mail VARCHAR(255) UNIQUE,    -- メールアドレス
  password VARCHAR(255) NOT NULL,    -- パスワード
  role_id INTEGER NOT NULL REFERENCES role(id),   -- アカウント種類ID
  -- updater INTEGER NOT NULL DEFAULT ,  -- 更新者
  created TIMESTAMP NOT NULL DEFAULT current_timestamp,    -- 作成日時
  updated TIMESTAMP NOT NULL DEFAULT current_timestamp,     -- 更新日時
  delflg INTEGER DEFAULT 0    -- 削除フラグ
);

-- ユーザーデータの挿入
-- INSERT INTO users (name, mail, password, role_id, created) VALUES('後藤 充晴','mh.goto.web@gmail.com','ca2ee2bce1ae1f4176ea22a57ef126b60b9a152f7d815f98d1b724db21f7619f', '1', current_timestamp);


-- 画像テーブルの作成
CREATE TABLE images (
  id SERIAL PRIMARY KEY,    -- 画像ID
  name VARCHAR(255) NOT NULL,    -- 画像名
  url VARCHAR(255) NOT NULL,    -- 画像url
  title VARCHAR(255),    -- title属性
  alt VARCHAR(255),    -- alt属性
  updater INTEGER NOT NULL,  -- 更新者
  created TIMESTAMP NOT NULL DEFAULT current_timestamp,    -- 作成日時
  updated TIMESTAMP NOT NULL DEFAULT current_timestamp,     -- 更新日時
  delflg INTEGER DEFAULT 0  -- 削除フラグ  
);

-- カテゴリテーブルの作成
CREATE TABLE categories (
  id SERIAL PRIMARY KEY,    -- カテゴリーID
  name VARCHAR(63) NOT NULL,    -- カテゴリー名
  updater INTEGER NOT NULL,  -- 更新者
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
  image_id INTEGER NOT NULL REFERENCES post_images(id),    -- カテゴリーID
  category_id INTEGER NOT NULL REFERENCES categories(id),    -- カテゴリーID
  status_id INTEGER NOT NULL REFERENCES statuses(id),    -- ステータスID
  updater INTEGER NOT NULL,  -- 更新者
  created TIMESTAMP NOT NULL DEFAULT current_timestamp,    -- 作成日時
  updated TIMESTAMP NOT NULL DEFAULT current_timestamp,    -- 更新日時
  delflg INTEGER DEFAULT 0    -- 削除フラグ
);

