---------------------------------------------------------------------------------------------------------
-- ▼ 下記については普段使用しないSQL
---------------------------------------------------------------------------------------------------------
-- DB作成
create database "postgresql-metric-60905";

-- 試し
INSERT INTO mo_users (mo_user_name, mo_user_mailaddress, mo_pass, mo_user_create) VALUES('goto1','goto@gmail.com','password', current_timestamp);
INSERT INTO mo_users (mo_user_name, mo_user_mailaddress, mo_pass, mo_user_create) VALUES('test','test@gmail.com','password', current_timestamp);
UPDATE mo_users SET mo_user_name = 'goto', mo_user_update = current_timestamp WHERE mo_user_id = '1';
ALTER TABLE mo_users ADD FOREIGN KEY (mo_account_id);

select * from mo_users;

-- リセット用
DELETE FROM mo_users;

DROP TABLE mo_accounts_cates;
DROP TABLE mo_categories;
DROP TABLE mo_images;
DROP TABLE mo_posts;
DROP TABLE mo_statuses;
DROP TABLE mo_users;

-- ユーザーテーブル ユーザーIDが2のユーザーを1に変更する
UPDATE mo_users SET mo_user_id = '1', mo_user_update = current_timestamp WHERE mo_user_id = '2';

-- カテゴリーテーブルに更新者カラムを追加する
ALTER TABLE mo_categories ADD mo_category_updater INTEGER;

-- 更新者カラムにNOT NULL制約を追加
ALTER TABLE mo_categories ALTER COLUMN mo_category_updater SET NOT NULL;

-- 更新者を結合して取得

-- SELECT 選択列リスト
-- FROM テーブルA
-- JOIN テーブルB
-- ON 両テーブルの結合条件

SELECT mo_category_id, mo_category_name,
        mo_category_create, mo_category_update,
        mo_user_name as mo_category_updater
FROM mo_users
JOIN mo_categories
ON mo_users.mo_user_id = mo_categories.mo_category_updater;

SELECT * FROM mo_users;
SELECT * FROM mo_categories;

-- mo_usersテーブルとmo_categoriesテーブルを結合して、
-- mo_categoriesテーブルのmo_category_updaterをidからユーザー名で表示
SELECT mo_category_id, mo_category_name,
mo_category_create, mo_category_update,
mo_user_name as mo_category_updater
FROM mo_users
JOIN mo_categories
ON mo_users.mo_user_id = mo_categories.mo_category_updater;

-- カテゴリー名を更新
UPDATE mo_categories SET mo_category_name = 'Javac', mo_category_update = current_timestamp, mo_category_updater = 4 WHERE mo_category_id = 1;

-- リセット用
DELETE FROM mo_categories WHERE mo_category_id = 1;


INSERT INTO mo_categories (mo_category_create, mo_category_updater) VALUES(current_timestamp, '4');


-- ユーザーの情報の更新
UPDATE mo_users SET mo_user_name = '更新テスト', mo_user_update = current_timestamp WHERE mo_user_id = 11;

SELECT mo_user_id, mo_user_name, mo_user_mailaddress, mo_pass, mo_account_id FROM mo_users;
SELECT * FROM mo_accounts_cates;

-- ユーザーの詳細情報
SELECT mo_user_id, mo_user_name,
mo_user_mailaddress, mo_pass,
mo_account_cate
FROM mo_users
JOIN mo_accounts_cates
ON mo_users.mo_account_id = mo_accounts_cates.mo_account_id;

SELECT * FROM mo_users 
RIGHT OUTER JOIN mo_accounts_cates
ON mo_users.mo_account_id = mo_accounts_cates.mo_account_id;



-- すでにあるテーブルにカラムを追加
ALTER TABLE mo_posts ADD mo_post_updater INTEGER NOT NULL;
ALTER TABLE mo_posts ADD mo_post_delflg INTEGER;

-- mo_postsテーブルから画像IDを削除
ALTER TABLE mo_posts DROP COLUMN mo_image_id;
UPDATE mo_statuses SET mo_status_name = '限定公開' WHERE mo_status_id = 3;


-- mo_post_delflgのデフォルトを0に変更
ALTER TABLE mo_posts ALTER COLUMN mo_post_delflg SET DEFAULT 0;
UPDATE mo_posts SET mo_post_title = '【読書ログ】スッキリ分かるサーブレット&JSP入門' WHERE mo_post_id = 2;

-- 投稿記事管理で取得するSQL


SELECT mo_user_id, mo_user_name FROM mo_users;
SELECT mo_category_id, mo_category_name FROM mo_categories;
SELECT mo_status_id, mo_status_name FROM mo_statuses;

UPDATE mo_posts SET mo_post_url = 'post_id2' WHERE mo_post_id = 8;
UPDATE mo_posts SET mo_post_url = 'post_id1' WHERE mo_post_id = 7;


-- 投稿記事作成のDAO用SQL
INSERT INTO mo_posts(mo_post_url,
mo_post_title, mo_post_description, mo_post_keyword,
mo_post_content, mo_category_id, mo_status_id, mo_post_updater)
VALUES(?, 
?, ?, ?,
?, ?, ?, ?);

-- 投稿記事編集
UPDATE mo_posts
SET mo_post_url = 'post_id2', mo_post_title = 'タイトル編集', mo_post_description = 'ディスクリプション編集',
mo_post_keyword = 'キーワード編集', mo_post_content = '内容編集', mo_category_id = 132, 
mo_status_id = 2, mo_post_update = current_timestamp, mo_post_updater = 3
WHERE mo_post_id = 11;

-- 投稿記事編集のDAO用SQL
UPDATE mo_posts
SET mo_post_url = ?, mo_post_title = ?, mo_post_description = ?,
mo_post_keyword = ?, mo_post_content = ?, mo_category_id = ?, 
mo_status_id = ?, mo_post_update = current_timestamp, mo_post_updater = ?
WHERE mo_post_id = ?;

-- 投稿記事テーブルのmo_post_urlカラムをユニークに設定
ALTER TABLE mo_posts ADD UNIQUE(mo_post_url);


-- 画像テーブルからmo_image_linkカラムを削除
ALTER TABLE mo_images DROP mo_image_link;
ALTER TABLE mo_images ADD mo_image_updater INTEGER;
ALTER TABLE mo_images ALTER COLUMN mo_image_updater SET NOT NULL;
ALTER TABLE mo_images ADD mo_image_delflg INTEGER;
ALTER TABLE mo_images ALTER COLUMN mo_image_delflg SET DEFAULT 0;

ALTER TABLE mo_images ADD UNIQUE(mo_image_name);

INSERT INTO
mo_images(mo_image_name, mo_image_url,
mo_image_title, mo_image_alt, mo_image_updater)
VALUES('test', 'post_id1', 'title', 'alt属性', 3);

UPDATE mo_images
SET mo_image_name = 'test', mo_image_url = 'post_id1', mo_image_title = 'title',
mo_image_alt = 'alt属性', mo_image_updater = 3;

ALTER TABLE mo_images ADD mo_image_name VARCHAR(255)

UPDATE mo_images
SET mo_image_url = 'test01', mo_image_title = '画像タイトルアップデート',
mo_image_alt = '代替テキスト', mo_image_update = current_timestamp, mo_image_updater = 3
WHERE mo_image_id = 17;

UPDATE mo_images
SET mo_image_url = ?, mo_image_title = ?,
mo_image_alt = ?, mo_image_update = current_timestamp,
mo_image_updater = ? WHERE mo_image_id = ?;


SELECT * FROM mo_posts
JOIN mo_categories ON mo_posts.mo_category_id = mo_categories.mo_category_id
WHERE mo_post_id = ?;

-- ブログ一覧の

-- 各カテゴリーの投稿一覧
SELECT * FROM mo_posts WHERE mo_category_id = 132;

SELECT * FROM mo_posts
JOIN mo_categories ON mo_posts.mo_category_id = mo_categories.mo_category_id
WHERE mo_category_id = ?;

SELECT * FROM mo_posts ORDER BY mo_post_update DESC;

SELECT row_number() FROM mo_posts;

SELECT ROW_NUMBER() OVER(), * FROM 
(SELECT mo_post_id, mo_post_title 
FROM mo_posts ORDER BY mo_post_update DESC) 
AS test;

SELECT ROW_NUMBER() OVER(ORDER BY mo_post_update DESC) AS num, mo_post_id, mo_post_title FROM mo_posts WHERE mo_post_id = 28;

SELECT mo_post_id, mo_post_title FROM mo_posts ORDER BY mo_post_update DESC LIMIT 1;
SELECT mo_post_id, mo_post_title FROM mo_posts ORDER BY mo_post_update DESC LIMIT (SELECT mo_post_id FROM mo_posts);

SELECT mo_post_id, mo_post_title, mo_post_url,
mo_posts.mo_category_id, mo_category_name, mo_posts.mo_status_id, mo_status_name,
mo_post_content, mo_post_update, mo_user_name AS mo_post_updater, mo_post_delflg
FROM mo_posts
JOIN mo_categories ON mo_posts.mo_category_id = mo_categories.mo_category_id
JOIN mo_statuses ON mo_posts.mo_status_id = mo_statuses.mo_status_id
JOIN mo_users ON mo_posts.mo_post_updater = mo_users.mo_user_id
ORDER BY mo_post_create DESC
LIMIT 10 OFFSET 0;

-- 選択した記事と同じカテゴリの最新を4件取得
SElECT mo_post_id, mo_post_title
FROM mo_posts
JOIN mo_categories ON mo_posts.mo_category_id = mo_categories.mo_category_id
JOIN mo_statuses ON mo_posts.mo_status_id = mo_statuses.mo_status_id
WHERE
mo_posts.mo_status_id = 2 AND
mo_posts.mo_category_id = 132 AND
mo_post_id != 44
ORDER BY mo_post_create DESC
LIMIT 4 OFFSET 0;

-- 投稿記事詳細 カテゴリーとアイキャッチ画像のジョイン
SELECT * FROM mo_posts
JOIN mo_categories ON mo_posts.mo_category_id = mo_categories.mo_category_id
JOIN mo_post_images ON mo_posts.mo_post_image_id = mo_post_images.id
WHERE mo_post_id = 77;


-- OUTER JOIN とINNER JOIN
SELECT * FROM mo_posts
JOIN mo_statuses ON mo_posts.mo_status_id = mo_statuses.mo_status_id
LEFT OUTER JOIN mo_post_images ON mo_posts.mo_post_image_id = mo_post_images.id
WHERE mo_posts.mo_status_id = 2;



SELECT mo_post_id, mo_post_title FROM mo_posts ORDER BY mo_post_update DESC LIMIT 1 OFFSET 1;

-- 連番更新
@i := 0;
UPDATE mo_posts SET mo_post_id = (@i := @i + 1);

SELECT MAX(mo_post_id) + 1 FROM mo_posts;

UPDATE mo_posts SET mo_post_id = 39 WHERE mo_post_id = 39;

SELECT mo_category_id, mo_category_name FROM mo_categories;

SELECT mo_category_id, mo_category_name, mo_category_create, 
mo_category_update, mo_user_name as mo_category_updater 
FROM mo_users JOIN mo_categories ON mo_users.mo_user_id = mo_categories.mo_category_updater 
ORDER BY mo_category_update DESC


INSERT INTO mo_posts(mo_post_url,
mo_post_title, mo_post_description, mo_post_keyword,
mo_post_content,
mo_category_id, mo_status_id,
mo_post_updater, mo_post_create, mo_post_update
) VALUES('post_id1',
'タイトル', 'ディスクリプション', 'キーワード',
'記事の内容',
122, 1,
3, current_timestamp, current_timestamp);

-- システム用
INSERT INTO mo_posts(mo_post_url,
mo_post_title, mo_post_description, mo_post_keyword,
mo_post_content, mo_category_id, mo_status_id,
mo_post_updater, mo_post_create, mo_post_update
) VALUES('post_id2', 
'タイトル', 'ディスクリプション', 'キーワード',
'記事の内容', 122, 1,
3, current_timestamp, current_timestamp);

INSERT INTO mo_categories (mo_category_name, mo_category_create, mo_category_updater) VALUES('雑記', current_timestamp, '4');
INSERT INTO mo_categories (mo_category_name, mo_category_create, mo_category_updater) VALUES('カテゴリー', current_timestamp, '3');



ALTER TABLE mo_posts DROP CONSTRAINT mo_post_images;

INSERT
INTO mo_posts(mo_post_url,
mo_post_title, mo_post_description, mo_post_keyword,
mo_post_content, mo_post_image_id, mo_category_id, mo_status_id, mo_post_updater)
VALUES ('post_id1', '投稿テスト', 'ディスクリプション', 'keyword',
'投稿テスト', 0, 1, 1, 2);

ALTER TABLE mo_posts DROP CONSTRAINT IF EXISTS mo_post_image_id;
INSERT INTO mo_post_images VALUES(0,'dummy','dummy','dummy','dummy');


-- 記事が公開されているカテゴリーの一覧
SELECT mo_category_id, mo_category_name
FROM mo_categories WHERE mo_category_id = (SELECT mo_category_id FROM mo_posts);

SELECT mo_category_id, mo_category_name FROM mo_categories
INNER JOIN mo_posts ON mo_posts.mo_category_id = mo_categories.mo_category_id
WHERE mo_posts.mo_status_id = 2
GROUP BY mo_category_id, mo_category_name;


select
    *
from
    users
;


-- カラム名の変更
ALTER TABLE posts RENAME image_id TO top_image_id;

-- NOT NULL制約を外す
ALTER TABLE posts ALTER COLUMN image_id DROP NOT NULL;

DROP TABLE posts;

-- カテゴリーテーブルに更新者カラムを追加する
ALTER TABLE posts ADD short_content VARCHAR(120);
