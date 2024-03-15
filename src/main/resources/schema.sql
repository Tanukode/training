DROP TABLE IF EXISTS "user_authorities";

CREATE TABLE "public"."user_authorities" (
    "user_uid" uuid NOT NULL,
    "authority_id" integer NOT NULL
) WITH (oids = false);

DROP TABLE IF EXISTS "authority";

DROP SEQUENCE IF EXISTS authority_id_seq;

CREATE SEQUENCE authority_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1;

CREATE TABLE "public"."authority" (
    "id" integer DEFAULT nextval('authority_id_seq') NOT NULL,
    "name" character varying(255) NOT NULL,
    CONSTRAINT "authority_pkey" PRIMARY KEY ("id")
) WITH (oids = false);

DROP TABLE IF EXISTS "user";

CREATE TABLE "public"."user" (
    "uid" uuid DEFAULT gen_random_uuid() NOT NULL,
    "username" character varying(255) NOT NULL,
    "email" character varying(255) NOT NULL,
    "password" character varying(255) NOT NULL,
    "first_name" character varying(255),
    "last_name" character varying(255),
    CONSTRAINT "user_pkey" PRIMARY KEY ("uid")
) WITH (oids = false);

INSERT INTO
    "user" (
        "uid",
        "username",
        "email",
        "password",
        "first_name",
        "last_name"
    )
VALUES
    (
        '6e2a77d9-b401-4f3e-9189-d856cf3b4e14',
        'test',
        'test',
        '$2a$10$XL.V/Ldz0KG0rArtjxKJQOBmTeQhm6JXY3.8iV9zuI.2cXqChoqPO',
        'test',
        'test'
    ),
    (
        '431221fc-aaa9-4215-9806-680a211c4875',
        'test2',
        'test2',
        '$2a$10$XL.V/Ldz0KG0rArtjxKJQOBmTeQhm6JXY3.8iV9zuI.2cXqChoqPO',
        NULL,
        NULL
    );

INSERT INTO
    "authority" ("id", "name")
VALUES
    (1, 'ROLE_USER'),
    (2, 'ROLE_ADMIN');

INSERT INTO
    "user_authorities" ("user_uid", "authority_id")
VALUES
    ('6e2a77d9-b401-4f3e-9189-d856cf3b4e14', 1),
    ('6e2a77d9-b401-4f3e-9189-d856cf3b4e14', 2),
    ('431221fc-aaa9-4215-9806-680a211c4875', 1);

ALTER TABLE
    ONLY "public"."user_authorities"
ADD
    CONSTRAINT "user_authorities_authority_id_fkey" FOREIGN KEY (authority_id) REFERENCES authority(id) NOT DEFERRABLE;

ALTER TABLE
    ONLY "public"."user_authorities"
ADD
    CONSTRAINT "user_authorities_user_uid_fkey" FOREIGN KEY (user_uid) REFERENCES "user"(uid) NOT DEFERRABLE;