function translate(type) {
    switch (type) {
        case 'audio':
            return '语音消息';

        case 'text':
            return '文字消息';

        case 'faipaiText':
            return '免费翻牌';

        case 'live':
            return '露脸直播';

        case 'diantai':
            return '电台直播';

        case 'idolFlip':
            return '鸡腿普通翻牌';

        case 'image':
            return '图片消息';

        case 'videoRecord':
            return '视频消息';
    }
    // return type;
}