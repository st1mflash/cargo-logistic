--todo вынести отдельно create, insert и тд
CREATE TABLE IF NOT EXISTS public.pack_model
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    name text COLLATE pg_catalog."default" NOT NULL,
    code character(1) COLLATE pg_catalog."default" NOT NULL,
    scheme text COLLATE pg_catalog."default" NOT NULL,
    scheme_width integer NOT NULL,
    scheme_height integer NOT NULL,
    CONSTRAINT pack_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.pack_model
    OWNER to postgres;

INSERT INTO pack_model (name, code, scheme, scheme_width, scheme_height)
VALUES ('стандарт_1', '1', '1', 1, 1);

INSERT INTO pack_model (name, code, scheme, scheme_width, scheme_height)
VALUES ('стандарт_2', '2', '11', 2, 1);

INSERT INTO pack_model (name, code, scheme, scheme_width, scheme_height)
VALUES ('стандарт_3', '3', '111', 3, 1);

INSERT INTO pack_model (name, code, scheme, scheme_width, scheme_height)
VALUES ('стандарт_4', '4', '1111', 4, 1);

INSERT INTO pack_model (name, code, scheme, scheme_width, scheme_height)
VALUES ('стандарт_5', '5', '11111', 5, 1);

INSERT INTO pack_model (name, code, scheme, scheme_width, scheme_height)
VALUES ('стандарт_6', '6', '111111', 3, 2);

INSERT INTO pack_model (name, code, scheme, scheme_width, scheme_height)
VALUES ('стандарт_7', '7', '11111110', 4, 2);

INSERT INTO pack_model (name, code, scheme, scheme_width, scheme_height)
VALUES ('стандарт_8', '8', '11111111', 4, 2);

INSERT INTO pack_model (name, code, scheme, scheme_width, scheme_height)
VALUES ('стандарт_9', '9', '111111111', 3, 3);