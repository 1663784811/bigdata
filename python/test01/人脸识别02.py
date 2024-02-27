import cv2
import numpy as np

# 加载两张人脸图像
image1 = cv2.imread("1.jpg")
image2 = cv2.imread("4.jpg")

# 转换为灰度图像
gray1 = cv2.cvtColor(image1, cv2.COLOR_BGR2GRAY)
gray2 = cv2.cvtColor(image2, cv2.COLOR_BGR2GRAY)

# 计算直方图
hist1 = cv2.calcHist([gray1], [0], None, [256], [0, 256])
hist2 = cv2.calcHist([gray2], [0], None, [256], [0, 256])

# 归一化直方图
hist1 = cv2.normalize(hist1, hist1).flatten()
hist2 = cv2.normalize(hist2, hist2).flatten()

# 计算巴氏距离
similarity = cv2.compareHist(hist1, hist2, cv2.HISTCMP_BHATTACHARYYA)

# 显示结果
if similarity < 0.5:  # 设置相似度阈值
    print("这两张人脸相似")
else:
    print("这两张人脸不相似")

# 显示图像
cv2.imshow("Face 1", image1)
cv2.imshow("Face 2", image2)
cv2.waitKey(0)
cv2.destroyAllWindows()
