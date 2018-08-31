set sql_safe_updates=0;

UPDATE t_memberinfo 
SET 
    background_color = 'rgba(255, 99, 132, 0.2)',
    border_color = 'rgba(255,99,132,1)'
WHERE
    support_color = '红';
    
    
    UPDATE t_memberinfo 
SET 
    background_color = 'rgba(54, 162, 235, 0.2)',
    border_color = 'rgba(54, 162, 235, 1)'
WHERE
    support_color in( '蓝','深蓝','浅葱','天蓝','青');
    
        UPDATE t_memberinfo 
SET 
    background_color = 'rgba(255, 206, 86, 0.2)',
    border_color = 'rgba(255, 206, 86, 1)'
WHERE
    support_color = '黄';
    
        UPDATE t_memberinfo 
SET 
    background_color = 'rgba(75, 192, 192, 0.2)',
    border_color = 'rrgba(75, 192, 192, 1)'
WHERE
    support_color = '绿';
    
        UPDATE t_memberinfo 
SET 
    background_color = 'rgba(153, 102, 255, 0.2)',
    border_color = 'rgba(153, 102, 255, 1)'
WHERE
    support_color = '紫';
    
        UPDATE t_memberinfo 
SET 
    background_color = 'rgba(255, 159, 64, 0.2)',
    border_color = 'rgba(255, 159, 64, 1)'
WHERE
    support_color = '橙';
    
            UPDATE t_memberinfo 
SET 
    background_color = 'rgba(205, 201, 201, 0.2)',
    border_color = 'rgba(205, 201, 201, 1)'
WHERE
    support_color = '白';
    
      UPDATE t_memberinfo 
SET 
    background_color = 'rgba(255, 20, 147, 0.2)',
    border_color = 'rgba(255, 20, 147, 1)'
WHERE
    support_color = '粉';
    
    
set sql_safe_updates=1;