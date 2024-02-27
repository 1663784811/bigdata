import cv2
import numpy as np

# 加载人脸检测器
face_cascade = cv2.CascadeClassifier(cv2.data.haarcascades + 'haarcascade_frontalface_default.xml')

# 加载两张人脸图像
image1 = cv2.imread("1.jpg")
image2 = cv2.imread("1.jpg")

# 转换为灰度图像
gray1 = cv2.cvtColor(image1, cv2.COLOR_BGR2GRAY)
gray2 = cv2.cvtColor(image2, cv2.COLOR_BGR2GRAY)

# 使用人脸检测器检测人脸
faces1 = face_cascade.detectMultiScale(gray1, scaleFactor=1.1, minNeighbors=5, minSize=(30, 30))
faces2 = face_cascade.detectMultiScale(gray2, scaleFactor=1.1, minNeighbors=5, minSize=(30, 30))

# 如果检测到人脸，则提取特征并计算相似度
if len(faces1) > 0 and len(faces2) > 0:
    # 提取第一张图像中的人脸特征
    x1, y1, w1, h1 = faces1[0]
    roi1 = gray1[y1:y1+h1, x1:x1+w1]
    hist1 = cv2.calcHist([roi1], [0], None, [256], [0, 256])
    hist1 = cv2.normalize(hist1, hist1).flatten()

    # 提取第二张图像中的人脸特征
    x2, y2, w2, h2 = faces2[0]
    roi2 = gray2[y2:y2+h2, x2:x2+w2]
    hist2 = cv2.calcHist([roi2], [0], None, [256], [0, 256])
    hist2 = cv2.normalize(hist2, hist2).flatten()

    # 计算欧氏距离
    similarity = np.linalg.norm(hist1 - hist2)

    # 显示结果
    if similarity < 0.5:  # 设置相似度阈值
        print("这两张人脸相似")
    else:
        print("这两张人脸不相似")
else:
    print("未检测到人脸")

# 显示图像
cv2.imshow("Face 1", image1)
cv2.imshow("Face 2", image2)
cv2.waitKey(0)
cv2.destroyAllWindows()
