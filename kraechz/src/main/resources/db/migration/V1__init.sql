create table if not exists kraechz_posts
(
    post_id int auto_increment,
    handle varchar(255) not null,
    text varchar(140) null,
    constraint kraechz_posts_pk
        primary key (post_id)
);

