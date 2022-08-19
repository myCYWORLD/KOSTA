alter system set nls_date_format='YY/MM/DD' scope=spfile;
alter system set nls_timestamp_format='YY/MM/DD HH:MI:SS' scope=spfile;
select sysdate ||'ป็น๘' from dual;

SELECT name, value$ FROM sys.props$
WHERE name='NLS_CHARACTERSET';

SELECT name, value$ FROM sys.props$
WHERE name='NLS_LANGUAGE';

UPDATE sys.props$
SET value$=
