SELECT 
    m.room_id,
    m.message_type,
    DATE(m.msg_time) msg_date,
    m.message_type
FROM
    t_roommsg m
WHERE
    m.message_type = 'diantai'
        AND DATE_FORMAT(m.msg_time, '%Y%m') = DATE_FORMAT(CURRENT_DATE(), '%Y%m')
GROUP BY m.room_id , msg_date
HAVING COUNT(msg_date) >= 1;

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
LIMIT 16;

SELECT 
    (SELECT 
            info.member_team
        FROM
            t_memberinfo info
        WHERE
            info.room_id = msg.room_id) team,
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
            AND DATE_FORMAT(m.msg_time, '%Y%m') = DATE_FORMAT(CURRENT_DATE(), '%Y%m')) msg
GROUP BY team;


SELECT 
    (SELECT 
            info.member_team
        FROM
            t_memberinfo info
        WHERE
            info.room_id = msg.room_id) team,
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
            AND DATE_FORMAT(m.msg_time, '%Y%m') = DATE_FORMAT(CURRENT_DATE(), '%Y%m'))) msg
GROUP BY team;

SELECT 
    info.member_name name, info.member_team team, rank.counts
FROM
    (SELECT 
        m.room_id, COUNT(m.room_id) counts
    FROM
        (SELECT 
        msg.room_id, DATE(msg.msg_time) msg_date
    FROM
        t_roommsg msg
    WHERE
        msg.message_type IN ('diantai' , 'live')
            AND DATE_FORMAT(msg.msg_time, '%Y%m') = DATE_FORMAT(CURRENT_DATE(), '%Y%m')
    GROUP BY msg.room_id , msg_date
    HAVING COUNT(msg_date) >= 1) m
    GROUP BY m.room_id) rank
        INNER JOIN
    t_memberinfo info ON rank.room_id = info.room_id
ORDER BY counts DESC
LIMIT 7;