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
            info.room_id = msg.room_id) name,
    (SELECT 
            info.member_team
        FROM
            t_memberinfo info
        WHERE
            info.room_id = msg.room_id) team, 
            msg.type type,
    COUNT(msg.room_id) counts
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
    HAVING COUNT(msg_date) >= 1) msg
GROUP BY msg.room_id
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