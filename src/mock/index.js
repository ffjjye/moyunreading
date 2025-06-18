// 模拟数据
export const mockUser = {
  id: 1,
  username: 'fjy',
  avatar: require('@/assets/avatar.jpg'),
  email: 'test@example.com',
  bio: '这是一个测试用户账号',
  joinDate: '2024-01-01',
  followers: 128,
  following: 256
};

export const mockGroups = [
  {
    id: 1,
    name: '读书会',
    description: '分享读书心得，交流阅读体验',
    memberCount: 156,
    bookCount: 89,
    createTime: '2024-01-01',
    avatar: 'group1.jpg'
  },
  {
    id: 2,
    name: '文学爱好者',
    description: '文学创作与欣赏',
    memberCount: 89,
    bookCount: 45,
    createTime: '2024-01-02',
    avatar: 'group2.jpg'
  }
];

export const mockBooks = [
  {
    id: 1,
    title: '红楼梦',
    author: '[清] 曹雪芹 著 / 高鹗',
    cover: require('@/assets/book-covers/hongloumeng.jpg'),
    description: '《红楼梦》是一部百科全书式的长篇小说。以宝黛爱情悲剧为主线，以四大家族的荣辱兴衰为背景，描绘出18世纪中国封建社会的方方面面，以及封建专制下新兴资本主义民主思想的萌动。结构宏大、情节委婉、细节精致，人物形象栩栩如生，声口毕现，堪称中国古代小说中的经典。',
    rating: 9.7,
    reviewCount: 445601,
    publishDate: '1996-12',
    publisher: '人民文学出版社',
    pages: 1606,
    price: '59.70元',
    isbn: '9787020002207',
    category: '中国古典文学',
    tags: ['古典文学', '四大名著', '爱情']
  },
  {
    id: 2,
    title: '悉达多',
    author: '[德] 赫尔曼·黑塞',
    cover: require('@/assets/book-covers/xiduo.jpg'),
    description: '《悉达多》并非是佛陀的故事，它讲述了一个人的一生，千万寻常人亦会经历的一生。意气风发的少年郎，常认为自己是被命运选中的人。抛下过去，随了跌跌撞撞的步伐，找寻心中的声音，追逐名利，经历友情，品尝爱情。不同的是，悉达多一生追求的是生命的圆融统一，看似宏大，确是每个人无法回避的问题。',
    rating: 9.2,
    reviewCount: 150030,
    publishDate: '2017-1',
    publisher: '天津人民出版社',
    pages: 144,
    price: '32.00元',
    isbn: '9787201112695',
    category: '外国文学',
    tags: ['哲学', '人生', '成长']
  },
  {
    id: 3,
    title: '被讨厌的勇气',
    author: '岸见一郎 / 古贺史健',
    cover: require('@/assets/book-covers/beitaoyan.jpg'),
    description: '本书用"青年与哲人的对话"这一故事形式总结了与弗洛伊德、荣格并称为"心理学三大巨头"的阿尔弗雷德·阿德勒的思想(阿德勒心理学)。风靡欧美的阿德勒心理学对于"人如何能够获得幸福"这个哲学问题给出了极其简单而又具体的"答案"。',
    rating: 8.3,
    reviewCount: 40294,
    publishDate: '2021-1',
    publisher: '机械工业出版社',
    pages: 194,
    price: '55.00元',
    isbn: '9787111495482',
    category: '心理学',
    tags: ['心理学', '哲学', '自我成长']
  }
];

export const mockReviews = [
  {
    id: 1,
    bookId: 1,
    userId: 1,
    username: '测试用户',
    content: '《红楼梦》不愧为中国古典文学的巅峰之作。曹雪芹以细腻的笔触描绘了一个封建大家族的兴衰，通过宝黛的爱情悲剧，展现了人性的复杂和社会的矛盾。每个人物都栩栩如生，情节环环相扣，读来令人回味无穷。',
    rating: 5,
    createTime: '2024-01-01',
    likes: 45
  },
  {
    id: 2,
    bookId: 2,
    userId: 1,
    username: '测试用户',
    content: '《悉达多》是一本充满哲思的小说。黑塞通过悉达多的求道之路，展现了人生追求真理的过程。书中对生命、爱情、欲望的思考深刻而富有诗意，读来令人深思。',
    rating: 5,
    createTime: '2024-01-02',
    likes: 32
  },
  {
    id: 3,
    bookId: 3,
    userId: 1,
    username: '测试用户',
    content: '《被讨厌的勇气》以对话的形式深入浅出地介绍了阿德勒心理学。书中关于人际关系、自我接纳、人生意义的讨论非常实用，对现代人的心理困扰很有帮助。',
    rating: 4,
    createTime: '2024-01-03',
    likes: 28
  }
];

export const mockMessages = [
  {
    id: 1,
    fromUserId: 2,
    fromUsername: '用户A',
    content: '你好，很高兴认识你！',
    createTime: '2024-01-01 10:00:00'
  },
  {
    id: 2,
    fromUserId: 3,
    fromUsername: '用户B',
    content: '最近在读什么书？',
    createTime: '2024-01-02 15:30:00'
  }
];

export const mockUpdates = [
  {
    id: 1,
    userId: 1,
    username: '测试用户',
    content: '今天读完了《红楼梦》，被曹雪芹细腻的笔触和深刻的人性描写所震撼。每个人物都栩栩如生，情节环环相扣，不愧为中国古典文学的巅峰之作。',
    createTime: '2024-01-01 14:30:00',
    likes: 45,
    comments: 12
  },
  {
    id: 2,
    userId: 1,
    username: '测试用户',
    content: '《悉达多》让我对人生有了新的思考。黑塞通过悉达多的求道之路，展现了生命的意义和追求真理的过程。',
    createTime: '2024-01-02 09:15:00',
    likes: 32,
    comments: 8
  },
  {
    id: 3,
    userId: 1,
    username: '测试用户',
    content: '《被讨厌的勇气》中的阿德勒心理学观点很有启发性，特别是关于人际关系和自我接纳的讨论，对现代人的心理困扰很有帮助。',
    createTime: '2024-01-03 16:20:00',
    likes: 28,
    comments: 6
  }
]; 