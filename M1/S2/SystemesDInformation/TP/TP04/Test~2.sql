select ville_region from LeClient
select capacite from stations

select count(*) from sejour81 where nbjours>81

select regexp_substr(ville_region,'[^:]+',1,1), count(regexp_substr(ville_region,'[^:]+',1,2)) from LeClient
group by regexp_substr(ville_region, '[^:]+',1,1)

select x.station, x.   
