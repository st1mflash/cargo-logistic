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