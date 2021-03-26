package com.maple.baiduocr.entity;

public class IDCardEntity {


    private WordsResultBean words_result;
    private long log_id;
    private int words_result_num;
    private int idcard_number_type;
    private String image_status;
    private String error_msg;
    private int error_code;

    public String getError_msg() {
        return error_msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public WordsResultBean getWords_result() {
        return words_result;
    }

    public void setWords_result(WordsResultBean words_result) {
        this.words_result = words_result;
    }

    public long getLog_id() {
        return log_id;
    }

    public void setLog_id(long log_id) {
        this.log_id = log_id;
    }

    public int getWords_result_num() {
        return words_result_num;
    }

    public void setWords_result_num(int words_result_num) {
        this.words_result_num = words_result_num;
    }

    public int getIdcard_number_type() {
        return idcard_number_type;
    }

    public void setIdcard_number_type(int idcard_number_type) {
        this.idcard_number_type = idcard_number_type;
    }

    public String getImage_status() {
        return image_status;
    }

    public void setImage_status(String image_status) {
        this.image_status = image_status;
    }

    public static class WordsResultBean {


        private 姓名Bean 姓名;
        private 民族Bean 民族;
        private 住址Bean 住址;
        private 公民身份号码Bean 公民身份号码;
        private 出生Bean 出生;
        private 性别Bean 性别;

        public 姓名Bean get姓名() {
            return 姓名;
        }

        public void set姓名(姓名Bean 姓名) {
            this.姓名 = 姓名;
        }

        public 民族Bean get民族() {
            return 民族;
        }

        public void set民族(民族Bean 民族) {
            this.民族 = 民族;
        }

        public 住址Bean get住址() {
            return 住址;
        }

        public void set住址(住址Bean 住址) {
            this.住址 = 住址;
        }

        public 公民身份号码Bean get公民身份号码() {
            return 公民身份号码;
        }

        public void set公民身份号码(公民身份号码Bean 公民身份号码) {
            this.公民身份号码 = 公民身份号码;
        }

        public 出生Bean get出生() {
            return 出生;
        }

        public void set出生(出生Bean 出生) {
            this.出生 = 出生;
        }

        public 性别Bean get性别() {
            return 性别;
        }

        public void set性别(性别Bean 性别) {
            this.性别 = 性别;
        }

        public static class 姓名Bean {


            private String words;
            private LocationBean location;

            public String getWords() {
                return words;
            }

            public void setWords(String words) {
                this.words = words;
            }

            public LocationBean getLocation() {
                return location;
            }

            public void setLocation(LocationBean location) {
                this.location = location;
            }

            public static class LocationBean {
                /**
                 * top : 341
                 * left : 164
                 * width : 89
                 * height : 37
                 */

                private int top;
                private int left;
                private int width;
                private int height;

                public int getTop() {
                    return top;
                }

                public void setTop(int top) {
                    this.top = top;
                }

                public int getLeft() {
                    return left;
                }

                public void setLeft(int left) {
                    this.left = left;
                }

                public int getWidth() {
                    return width;
                }

                public void setWidth(int width) {
                    this.width = width;
                }

                public int getHeight() {
                    return height;
                }

                public void setHeight(int height) {
                    this.height = height;
                }
            }
        }

        public static class 民族Bean {
            /**
             * words : 汉
             * location : {"top":400,"left":301,"width":20,"height":29}
             */

            private String words;
            private LocationBeanX location;

            public String getWords() {
                return words;
            }

            public void setWords(String words) {
                this.words = words;
            }

            public LocationBeanX getLocation() {
                return location;
            }

            public void setLocation(LocationBeanX location) {
                this.location = location;
            }

            public static class LocationBeanX {
                /**
                 * top : 400
                 * left : 301
                 * width : 20
                 * height : 29
                 */

                private int top;
                private int left;
                private int width;
                private int height;

                public int getTop() {
                    return top;
                }

                public void setTop(int top) {
                    this.top = top;
                }

                public int getLeft() {
                    return left;
                }

                public void setLeft(int left) {
                    this.left = left;
                }

                public int getWidth() {
                    return width;
                }

                public void setWidth(int width) {
                    this.width = width;
                }

                public int getHeight() {
                    return height;
                }

                public void setHeight(int height) {
                    this.height = height;
                }
            }
        }

        public static class 住址Bean {


            private String words;
            private LocationBeanXX location;

            public String getWords() {
                return words;
            }

            public void setWords(String words) {
                this.words = words;
            }

            public LocationBeanXX getLocation() {
                return location;
            }

            public void setLocation(LocationBeanXX location) {
                this.location = location;
            }

            public static class LocationBeanXX {

                private int top;
                private int left;
                private int width;
                private int height;

                public int getTop() {
                    return top;
                }

                public void setTop(int top) {
                    this.top = top;
                }

                public int getLeft() {
                    return left;
                }

                public void setLeft(int left) {
                    this.left = left;
                }

                public int getWidth() {
                    return width;
                }

                public void setWidth(int width) {
                    this.width = width;
                }

                public int getHeight() {
                    return height;
                }

                public void setHeight(int height) {
                    this.height = height;
                }
            }
        }

        public static class 公民身份号码Bean {


            private String words;
            private LocationBeanXXX location;

            public String getWords() {
                return words;
            }

            public void setWords(String words) {
                this.words = words;
            }

            public LocationBeanXXX getLocation() {
                return location;
            }

            public void setLocation(LocationBeanXXX location) {
                this.location = location;
            }

            public static class LocationBeanXXX {


                private int top;
                private int left;
                private int width;
                private int height;

                public int getTop() {
                    return top;
                }

                public void setTop(int top) {
                    this.top = top;
                }

                public int getLeft() {
                    return left;
                }

                public void setLeft(int left) {
                    this.left = left;
                }

                public int getWidth() {
                    return width;
                }

                public void setWidth(int width) {
                    this.width = width;
                }

                public int getHeight() {
                    return height;
                }

                public void setHeight(int height) {
                    this.height = height;
                }
            }
        }

        public static class 出生Bean {


            private String words;
            private LocationBeanXXXX location;

            public String getWords() {
                return words;
            }

            public void setWords(String words) {
                this.words = words;
            }

            public LocationBeanXXXX getLocation() {
                return location;
            }

            public void setLocation(LocationBeanXXXX location) {
                this.location = location;
            }

            public static class LocationBeanXXXX {


                private int top;
                private int left;
                private int width;
                private int height;

                public int getTop() {
                    return top;
                }

                public void setTop(int top) {
                    this.top = top;
                }

                public int getLeft() {
                    return left;
                }

                public void setLeft(int left) {
                    this.left = left;
                }

                public int getWidth() {
                    return width;
                }

                public void setWidth(int width) {
                    this.width = width;
                }

                public int getHeight() {
                    return height;
                }

                public void setHeight(int height) {
                    this.height = height;
                }
            }
        }

        public static class 性别Bean {


            private String words;
            private LocationBeanXXXXX location;

            public String getWords() {
                return words;
            }

            public void setWords(String words) {
                this.words = words;
            }

            public LocationBeanXXXXX getLocation() {
                return location;
            }

            public void setLocation(LocationBeanXXXXX location) {
                this.location = location;
            }

            public static class LocationBeanXXXXX {


                private int top;
                private int left;
                private int width;
                private int height;

                public int getTop() {
                    return top;
                }

                public void setTop(int top) {
                    this.top = top;
                }

                public int getLeft() {
                    return left;
                }

                public void setLeft(int left) {
                    this.left = left;
                }

                public int getWidth() {
                    return width;
                }

                public void setWidth(int width) {
                    this.width = width;
                }

                public int getHeight() {
                    return height;
                }

                public void setHeight(int height) {
                    this.height = height;
                }
            }
        }
    }
}
