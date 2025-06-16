create user actest identified by actest

grant resource, connect to actest

alter user actest default tablespace users

alter user actest temporary tablespace temp