select m.room_id  ,m.message_type,date(m.msg_time) msg_date,m.message_type
from t_roommsg m
where m.message_type='diantai'
and date_format(m.msg_time,'%Y%m')= date_format(current_date(),'%Y%m')
group by m.room_id,msg_date
having count(msg_date)>=1;

SELECT 
    (SELECT 
            info.member_name
        FROM
            t_memberinfo info
        WHERE
            info.room_id = desc.room_id) name,
    (SELECT 
            info.member_team
        FROM
            t_memberinfo info
        WHERE
            info.room_id = desc.room_id) team,
            desc.type type,
    COUNT(desc.room_id) counts
FROM
    (SELECT 
        m.room_id,
            DATE(m.msg_time) msg_date,
            m.message_type type
    FROM
        t_roommsg m
    WHERE
        m.message_type = 'diantai'
            AND DATE_FORMAT(m.msg_time, '%Y%m') = DATE_FORMAT(CURRENT_DATE(), '%Y%m')
    GROUP BY m.room_id , msg_date
    HAVING COUNT(msg_date) >= 1) desc
GROUP BY desc.room_id
ORDER BY counts DESC
limit 16;

SELECT 
    (SELECT 
            info.member_name
        FROM
            t_memberinfo info
        WHERE
            info.room_id = m.room_id) name,
    (SELECT 
            info.member_team
        FROM
            t_memberinfo info
        WHERE
            info.room_id = m.room_id) team,
    m.message_type type,
    COUNT(m.room_id) counts
FROM
    t_roommsg m
WHERE
    m.message_type = 'text'
        AND DATE_FORMAT(m.msg_time, '%Y%m') = DATE_FORMAT(CURRENT_DATE(), '%Y%m')
GROUP BY m.room_id
ORDER BY counts DESC
LIMIT 16;

SELECT 
    (SELECT 
            info.member_team
        FROM
            t_memberinfo info
        WHERE
            info.room_id = desc.room_id) team,
    COUNT(1) counts
FROM
    (SELECT 
        m.room_id
    FROM
        t_roommsg m
    WHERE
        (m.message_type = 'text'
            OR m.message_type = 'faipaiText'
            OR m.message_type = 'audio'
            OR m.message_type = 'idolFlip'
            OR m.message_type = 'image'
            OR m.message_type = 'videoRecord')
            AND DATE_FORMAT(m.msg_time, '%Y%m') = DATE_FORMAT(CURRENT_DATE(), '%Y%m')) desc
GROUP BY team;


SELECT 
    (SELECT 
            info.member_team
        FROM
            t_memberinfo info
        WHERE
            info.room_id = desc.room_id) team,
    COUNT(1) counts
FROM
    ((SELECT 
        m.room_id, m.msg_time
    FROM
        t_roommsg m
    WHERE
        m.message_type = 'text'
            AND DATE_FORMAT(m.msg_time, '%Y%m') = DATE_FORMAT(CURRENT_DATE(), '%Y%m')) UNION ALL (SELECT 
        m.room_id, m.msg_time
    FROM
        t_roommsg m
    WHERE
        m.message_type = 'faipaiText'
            AND DATE_FORMAT(m.msg_time, '%Y%m') = DATE_FORMAT(CURRENT_DATE(), '%Y%m')) UNION ALL (SELECT 
        m.room_id, m.msg_time
    FROM
        t_roommsg m
    WHERE
        m.message_type = 'audio'
            AND DATE_FORMAT(m.msg_time, '%Y%m') = DATE_FORMAT(CURRENT_DATE(), '%Y%m')) UNION ALL (SELECT 
        m.room_id, m.msg_time
    FROM
        t_roommsg m
    WHERE
        m.message_type = 'idolFlip'
            AND DATE_FORMAT(m.msg_time, '%Y%m') = DATE_FORMAT(CURRENT_DATE(), '%Y%m')) UNION ALL (SELECT 
        m.room_id, m.msg_time
    FROM
        t_roommsg m
    WHERE
        m.message_type = 'image'
            AND DATE_FORMAT(m.msg_time, '%Y%m') = DATE_FORMAT(CURRENT_DATE(), '%Y%m')) UNION ALL (SELECT 
        m.room_id, m.msg_time
    FROM
        t_roommsg m
    WHERE
        m.message_type = 'videoRecord'
            AND DATE_FORMAT(m.msg_time, '%Y%m') = DATE_FORMAT(CURRENT_DATE(), '%Y%m'))) desc
GROUP BY team;