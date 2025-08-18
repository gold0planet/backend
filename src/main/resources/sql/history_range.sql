-- 创建历史区间表
CREATE TABLE IF NOT EXISTS `history_range` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    `range_name` VARCHAR(100) NOT NULL COMMENT '区间名称',
    `range_type` VARCHAR(20) NOT NULL COMMENT '区间类型（birth/miles/hours）',
    `start_value` INT NOT NULL COMMENT '起始值',
    `end_value` INT NOT NULL COMMENT '结束值',
    `created_time` DATETIME NOT NULL COMMENT '创建时间',
    INDEX `idx_range_type` (`range_type`),
    INDEX `idx_created_time` (`created_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='历史区间表';

-- 插入示例数据
INSERT INTO `history_range` (`range_name`, `range_type`, `start_value`, `end_value`, `created_time`) VALUES
('青年期', 'birth', 1980, 2000, NOW()),
('中年期', 'birth', 1960, 1979, NOW()),
('短途飞行', 'miles', 0, 5000, NOW()),
('中途飞行', 'miles', 5001, 15000, NOW()),
('长途飞行', 'miles', 15001, 50000, NOW()),
('短时飞行', 'hours', 0, 10, NOW()),
('中时飞行', 'hours', 11, 30, NOW()),
('长时飞行', 'hours', 31, 100, NOW());
