CREATE TABLE IF NOT EXISTS public.car_model
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    name text COLLATE pg_catalog."default" NOT NULL,
    cargo_width integer NOT NULL,
    cargo_height integer NOT NULL,
    CONSTRAINT car_pkey PRIMARY KEY (id)
)
TABLESPACE pg_default;