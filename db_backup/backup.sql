

CREATE EXTENSION IF NOT EXISTS "uuid-ossp" WITH SCHEMA public;


COMMENT ON EXTENSION "uuid-ossp" IS 'generate universally unique identifiers (UUIDs)';


SET default_tablespace = '';

SET default_table_access_method = heap;



CREATE TABLE IF NOT EXISTS public.transactionlist (
    transactionid uuid DEFAULT public.uuid_generate_v4() NOT NULL,
    accid uuid NOT NULL,
    amount double precision NOT NULL,
    shortdes character varying(200),
    transactiondate timestamp(6) without time zone NOT NULL
);


INSERT INTO public.transactionlist (transactionid, accid, amount, shortdes, transactiondate) VALUES
('4572023e-69e3-47e7-ac5d-3c10dedc8303', 'd10d5e04-9a54-454b-897f-ad8c02af7193', 100.5, 'Taxi', '2024-05-01 00:00:00'),
('5d17bbcd-aceb-408a-8685-ec189f2cff11', '4c427cf2-e42f-4851-8149-e4bdc098e6c7', 250, 'Invoice #1234', '2024-05-02 00:00:00'),
('e4777b7b-8f6e-4f74-995a-7a2b53f3fa30', '4c427cf2-e42f-4851-8149-e4bdc098e6c7', 75, 'Subscription Fee', '2024-05-03 00:00:00'),
('d54b744f-4d46-4cb8-b4c9-b2098e0936bf', '9d3ffd11-bd3a-4fc9-afe8-5f83412f3b49', 100.5, 'Taxi', '2024-05-01 00:00:00'),
('0468feb1-d24f-42a8-8df2-65e445f97e2d', '9d3ffd11-bd3a-4fc9-afe8-5f83412f3b49', 250, 'Invoice #1234', '2024-05-02 00:00:00'),
('16a6df82-64db-4dfb-a008-5da59aa0a5cd', '9d3ffd11-bd3a-4fc9-afe8-5f83412f3b49', 75, 'Subscription Fee', '2024-05-03 00:00:00'),
('8cc73dbc-51d4-4537-a58f-0c72b877559d', '0c49f197-6132-4ac8-a0eb-01c8ad05f705', 120, 'Coffee Purchase', '2024-04-30 00:00:00'),
('ff5575b9-0f51-493e-9e00-0d1d92b53038', '6bfc75d6-8655-480e-9b99-985b6ae304ad', 150, 'Groceries', '2024-05-02 00:00:00'),
('ef53ca9a-8058-4d4f-a570-9cdd65e592d4', 'a752fd8a-c5ea-44a8-a409-a7ee21ec81c7', 350, 'Electronics', '2024-05-03 00:00:00'),
('c7659aa5-fdf2-428b-b574-600e6a92d7f4', '62504914-c4ef-4ec0-9a5e-f9e3b0457741', 75, 'Transport Ticket', '2024-05-05 00:00:00'),
('98046a23-5350-4c32-8a14-8e45e47bedf5', 'e20d1dfe-dbed-4a16-bbfc-db479f6af808', 95, 'Dinner', '2024-05-06 00:00:00'),
('e2e525e2-4e86-4f0b-9cb6-7b9534e5bea6', 'c7a6a248-3320-47d6-9d86-3f9dd1f75c74', 180, 'Utility Bill', '2024-05-07 00:00:00'),
('9b9f4acf-51dc-401b-9dbe-7f41a018931f', 'f3d6f5ac-a6a1-4eed-a5ac-6fa8be4c2767', 220, 'Gym Membership', '2024-05-09 00:00:00'),
('038889d0-c091-4e32-b383-e0d9430717dc', '4a0fd03c-ae7b-40fe-8049-d9ec925a49d3', 400, 'Flight Ticket', '2024-05-10 00:00:00'),
('f2e0c5bb-617a-4594-8ec0-2f4df69faa1d', 'e2e492a1-1560-4e45-b971-fd717e697f5e', 200, 'Book Purchase', '2024-05-01 00:00:00'),
('df51c7ca-8ddc-48b0-afd2-9d81f23d0a13', 'e2e492a1-1560-4e45-b971-fd717e697f5e', 250, 'Online Subscription', '2024-05-04 00:00:00'),
('1442f14b-4c6a-42ef-98b6-77bba68f60d1', 'e2e492a1-1560-4e45-b971-fd717e697f5e', 300, 'Health Insurance', '2024-05-08 00:00:00');



ALTER TABLE ONLY public.transactionlist
    ADD CONSTRAINT transactionlist_pkey PRIMARY KEY (transactionid); 

   
   CREATE TABLE IF NOT EXISTS public.debit (
    userid integer,
    accid uuid NOT NULL,
    accounttype character varying(250) DEFAULT 'Deposit'::character varying NOT NULL,
    accstats boolean DEFAULT false NOT NULL,
    balance double precision DEFAULT 0.0 NOT NULL,
    acccreationdate timestamp without time zone DEFAULT timezone('UTC+3'::text, CURRENT_TIMESTAMP)
);



CREATE TABLE IF NOT EXISTS public.deposit (
    userid integer,
    accid uuid NOT NULL,
    accounttype character varying(250) DEFAULT 'Deposit'::character varying NOT NULL,
    accstats boolean DEFAULT false NOT NULL,
    balance double precision DEFAULT 0.0 NOT NULL,
    acccreationdate timestamp without time zone DEFAULT timezone('UTC+3'::text, CURRENT_TIMESTAMP)
);




CREATE TABLE IF NOT EXISTS public.holder (
    userid integer NOT NULL,
    fullname character varying(100) NOT NULL,
    surname character varying(100) NOT NULL,
    email character varying(150) NOT NULL,
    phone character varying(15) NOT NULL,
    pass character varying(255) NOT NULL,
    gender character varying(150) NOT NULL,
    authstats boolean,
    userbirthdate date,
    user_creation_date timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    authip character varying(150),
    token_creation_date timestamp(6) without time zone,
    auth_token character varying(255)
);



INSERT INTO public.debit (userid, accid, accounttype, accstats, balance, acccreationdate) VALUES
(2, '0c49f197-6132-4ac8-a0eb-01c8ad05f705', 'Debit', 't', 0, '2024-05-08 02:12:21.067047'),
(2, '2f50e74c-f152-432c-9e0d-61a9e6396d1a', 'Debit', 't', 0, '2024-05-08 02:15:23.398615'),
(2, '6bfc75d6-8655-480e-9b99-985b6ae304ad', 'Debit', 't', 0, '2024-05-08 02:15:30.564555'),
(2, 'a752fd8a-c5ea-44a8-a409-a7ee21ec81c7', 'Debit', 't', 0, '2024-05-08 02:15:33.829885'),
(2, '6c0466c4-951b-4bf5-a9aa-f60419e97763', 'Debit', 't', 0, '2024-05-08 02:15:38.549157'),
(3, '62504914-c4ef-4ec0-9a5e-f9e3b0457741', 'Debit', 't', 0, '2024-05-08 02:15:54.151172'),
(3, 'e20d1dfe-dbed-4a16-bbfc-db479f6af808', 'Debit', 't', 0, '2024-05-08 02:15:57.057046'),
(3, 'c7a6a248-3320-47d6-9d86-3f9dd1f75c74', 'Debit', 't', 0, '2024-05-08 02:18:55.572955'),
(3, 'a50f3a40-f0c8-448b-9d80-cebf87417396', 'Debit', 't', 0, '2024-05-08 02:19:03.007252'),
(3, 'f3d6f5ac-a6a1-4eed-a5ac-6fa8be4c2767', 'Debit', 't', 0, '2024-05-08 02:19:05.800085'),
(3, '8519e5a0-9b92-477d-8f83-3767aa50b0bd', 'Debit', 't', 0, '2024-05-08 02:20:33.099057'),
(2, '61d00857-615d-45cd-b0b9-cb13fecb5645', 'Debit', 't', 0, '2024-05-08 02:21:42.721499'),
(1, 'bf43018b-a4e1-40a5-934e-d38d44a750be', 'Debit', 't', 0, '2024-05-08 02:22:03.547248');


INSERT INTO public.deposit (userid, accid, accounttype, accstats, balance, acccreationdate) VALUES
(2, 'e2e492a1-1560-4e45-b971-fd717e697f5e', 'Deposit', 't', 0, '2024-05-08 01:09:33.8177'),
(3, 'cde62b2b-bca4-43a9-b617-0004e93690a7', 'Deposit', 't', 0, '2024-05-08 01:45:45.966826'),
(3, 'e6f44344-23d5-48bc-8b4a-03efd0a76bac', 'Deposit', 't', 0, '2024-05-08 02:20:50.606483'),
(3, '9d3ffd11-bd3a-4fc9-afe8-5f83412f3b49', 'Deposit', 't', 0, '2024-05-08 02:20:56.77926'),
(3, 'd6fc5c6f-ee27-42d3-9127-cbf7aaa95788', 'Deposit', 't', 0, '2024-05-08 02:21:00.808584'),
(2, 'bde71174-3adb-4fff-88e2-3a556bf303c4', 'Deposit', 't', 0, '2024-05-08 02:21:27.597624'),
(2, 'd10d5e04-9a54-454b-897f-ad8c02af7193', 'Deposit', 't', 0, '2024-05-08 02:21:30.143845'),
(1, '4a0fd03c-ae7b-40fe-8049-d9ec925a49d3', 'Deposit', 't', 0, '2024-05-08 02:22:11.379802'),
(2, 'ae23b2f0-4fbd-43dc-b222-6504e84c321b', 'Deposit', 't', 0, '2024-05-08 19:05:49.378699'),
(2, 'fe28d686-4bdc-482f-bd9f-317a18f4ad8c', 'Deposit', 't', 0, '2024-05-08 19:09:20.837469'),
(2, '97eeb11e-f9d3-4af1-be1c-f1963a724b27', 'Deposit', 't', 0, '2024-05-08 19:09:53.62463'),
(2, 'c4b1646b-181e-4d3e-923e-59c3542a24ab', 'Deposit', 't', 0, '2024-05-08 22:28:33.551778'),
(2, 'cd80b422-ebdd-4736-9438-caa382cc47ad', 'Deposit', 't', 0, '2024-05-08 22:31:01.773313'),
(2, '8ac29d33-b69c-424e-a81c-cf8ed1e192d1', 'Deposit', 't', 0, '2024-05-10 14:09:54.621433');


INSERT INTO public.holder (userid, fullname, surname, email, phone, pass, gender, authstats, userbirthdate, user_creation_date, authip, token_creation_date, auth_token) VALUES
(3, 'Eren', 'Dag', 'yapimgenc@gmail.com', '05321252145', '123123', 'male', 't', '1997-01-01', '2024-05-08 01:02:38.359149', '0:0:0:0:0:0:0:1', '2024-05-08 02:15:48.790225', '634137de-fdad-49a5-a546-5863d3222a38'),
(1, 'zeynep', 'Tınaz', 'valohsp5@gmail.com', '05321252145', '123123', 'female', 't', '2000-01-01', '2024-05-08 00:45:13.382721', '0:0:0:0:0:0:0:1', '2024-05-08 09:07:52.90336', '4f400e8d-f1cc-46c2-b2cd-0b3f3e85c999'),
(4, 'ismail', 'turker', 'tamerturkerceza@hotmail.com', '05321252145', '123123', 'male', NULL, '1994-01-01', '2024-05-08 17:33:14.717684', NULL, NULL, NULL),
(2, 'Tamer', 'Turker', 'turkertamer41@gmail.com', '05321252145', '123123', 'male', 't', '1999-01-01', '2024-05-08 01:01:00.231009', '172.23.0.1', '2024-05-12 14:22:49.9629', '31d979d4-b4c1-4f64-84b7-c8538ba633ea');





ALTER TABLE ONLY public.deposit
    ADD CONSTRAINT deposit_pkey PRIMARY KEY (accid);



ALTER TABLE ONLY public.debit
    ADD CONSTRAINT deposit_pkey_1 PRIMARY KEY (accid);



ALTER TABLE ONLY public.holder
    ADD CONSTRAINT holder_pkey PRIMARY KEY (userid);



--
-- PostgreSQL database dump complete
--

