/usr/local/bin/redis-cli -a "fan48'redis" keys "*rank"| xargs /usr/local/bin/redis-cli -a "fan48'redis" del &
/usr/local/bin/redis-cli -a "fan48'redis" keys "*pocket_data"| xargs /usr/local/bin/redis-cli -a "fan48'redis" del &
/usr/local/bin/redis-cli -a "fan48'redis" keys "*counts"| xargs /usr/local/bin/redis-cli -a "fan48'redis" del &
