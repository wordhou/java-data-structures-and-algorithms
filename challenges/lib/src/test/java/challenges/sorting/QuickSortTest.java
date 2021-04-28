package challenges.sorting;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class QuickSortTest {
    int[][] arrays;
    int[][] expected;

    @Before
    public void setupArrays() {
        arrays = new int[][]{
                {},
                {1},
                {2, 1},
                {1, 2},
                {3, 2, 1},
                {3, 1, 2},
                {2, 1, 3},
                {2, 3, 1},
                {1, 3, 2},
                {1, 2, 3},

                {4, 3, 2, 1}, {1, 3, 2, 4},
                {0, 2, 4, 3, 1}, {0, 2, 4, 3, 1},
                {3, 0, 1, 2, 5, 4}, {3, 0, 1, 2, 5, 4},
                {0, 5, 1, 2, 6, 4, 3}, {0, 5, 1, 2, 6, 4, 3},

                {7, 1, 2, 5, 4, 8, 6, 9, 3, 0, 10, 11}, {1, 4, 9, 5, 11, 0, 8, 3, 10, 2, 6, 7}, {3, 9, 10, 1, 7, 2, 6, 5, 0, 11, 4, 8}, {7, 9, 4, 2, 5, 11, 10, 3, 8, 6, 0, 1},
                {21, 10, 8, 9, 3, 17, 22, 0, 13, 15, 6, 14, 1, 20, 11, 2, 19, 18, 5, 4, 12, 7, 16, 23}, {21, 10, 8, 9, 3, 17, 22, 0, 13, 15, 6, 14, 1, 20, 11, 2, 19, 18, 5, 4, 12, 7, 16, 23}, {21, 10, 8, 9, 3, 17, 22, 0, 13, 15, 6, 14, 1, 20, 11, 2, 19, 18, 5, 4, 12, 7, 16, 23}, {21, 10, 8, 9, 3, 17, 22, 0, 13, 15, 6, 14, 1, 20, 11, 2, 19, 18, 5, 4, 12, 7, 16, 23},

                {22, 9, 45, 72, 73, 56, 4, 81, 64, 77, 47, 60, 96, 28, 55, 10, 12, 98, 53, 52, 21, 5, 91, 90, 69, 80, 83, 43, 33, 37, 88, 14, 29, 26, 7, 95, 68, 86, 19, 94, 74, 6, 70, 15, 76, 63, 59, 67, 18, 82, 40, 38, 93, 46, 89, 32, 16, 36, 66, 99, 65, 1, 44, 25, 92, 13, 61, 97, 54, 85, 30, 42, 39, 58, 87, 34, 62, 8, 0, 48, 49, 71, 75, 78, 23, 84, 57, 27, 41, 17, 51, 31, 24, 50, 3, 35, 2, 79, 11, 20},
                {31, 25, 42, 8, 71, 28, 44, 58, 87, 23, 9, 38, 67, 39, 63, 90, 18, 69, 19, 88, 5, 47, 13, 30, 72, 56, 41, 91, 53, 97, 57, 50, 92, 54, 65, 0, 73, 85, 70, 22, 98, 11, 60, 12, 96, 52, 40, 4, 45, 86, 27, 35, 55, 32, 82, 21, 66, 84, 75, 49, 68, 10, 89, 59, 20, 62, 46, 7, 36, 64, 15, 29, 17, 34, 94, 16, 33, 76, 80, 24, 48, 81, 93, 14, 1, 74, 2, 77, 78, 61, 51, 95, 3, 37, 99, 79, 26, 83, 43, 6},
                {79, 56, 14, 98, 61, 51, 76, 20, 74, 26, 30, 87, 17, 13, 2, 88, 34, 28, 58, 92, 37, 42, 31, 12, 50, 32, 7, 44, 45, 24, 36, 5, 90, 62, 89, 59, 1, 81, 9, 10, 21, 40, 6, 19, 86, 53, 8, 96, 78, 93, 46, 77, 99, 55, 52, 85, 84, 27, 4, 39, 33, 73, 91, 97, 57, 69, 49, 35, 48, 38, 75, 71, 67, 72, 0, 70, 47, 82, 15, 65, 66, 43, 23, 60, 94, 41, 95, 54, 64, 11, 18, 25, 3, 29, 68, 63, 83, 16, 22, 80},
                {33, 48, 79, 75, 61, 42, 71, 65, 51, 81, 17, 56, 73, 88, 66, 63, 85, 83, 90, 92, 19, 12, 96, 24, 37, 95, 13, 16, 10, 21, 3, 62, 26, 47, 57, 53, 29, 41, 5, 43, 44, 67, 31, 7, 80, 18, 82, 97, 49, 40, 36, 27, 22, 58, 0, 50, 94, 55, 6, 69, 1, 2, 9, 77, 32, 93, 98, 23, 72, 30, 59, 11, 4, 68, 39, 28, 46, 78, 54, 8, 91, 76, 87, 74, 60, 89, 64, 52, 14, 20, 70, 34, 99, 84, 35, 86, 15, 45, 25, 38},
                {94, 58, 18, 57, 64, 60, 91, 37, 15, 59, 46, 0, 73, 92, 99, 80, 67, 72, 66, 49, 20, 53, 42, 74, 47, 65, 10, 7, 31, 63, 75, 69, 38, 30, 28, 33, 93, 13, 12, 32, 86, 68, 11, 61, 40, 55, 29, 88, 98, 97, 52, 83, 23, 43, 5, 96, 51, 48, 79, 82, 39, 89, 25, 1, 6, 8, 85, 14, 9, 34, 3, 4, 90, 76, 50, 56, 70, 26, 78, 81, 17, 16, 27, 2, 45, 22, 95, 36, 21, 77, 41, 19, 62, 84, 54, 71, 87, 44, 24, 35},
                {60, 94, 19, 54, 72, 27, 51, 71, 1, 81, 4, 87, 46, 59, 56, 57, 35, 85, 55, 37, 14, 73, 44, 50, 92, 8, 21, 30, 67, 79, 34, 2, 28, 36, 0, 47, 42, 98, 84, 11, 3, 53, 17, 99, 97, 29, 74, 90, 48, 52, 39, 93, 20, 77, 26, 62, 61, 78, 40, 96, 15, 75, 95, 18, 69, 6, 12, 76, 86, 41, 88, 82, 70, 64, 38, 22, 65, 16, 10, 25, 33, 83, 9, 32, 80, 91, 31, 66, 43, 63, 23, 24, 7, 68, 45, 58, 49, 5, 89, 13},
                {197, 208, 446, 216, 455, 482, 111, 12, 442, 134, 561, 161, 279, 21, 194, 296, 522, 141, 159, 500, 219, 544, 479, 571, 297, 542, 440, 465, 45, 591, 409, 521, 566, 438, 361, 433, 126, 278, 175, 436, 580, 204, 309, 185, 418, 143, 260, 467, 223, 524, 437, 147, 100, 267, 259, 23, 245, 50, 255, 132, 347, 94, 532, 420, 133, 18, 282, 32, 597, 413, 523, 564, 78, 124, 431, 105, 237, 200, 217, 41, 61, 274, 556, 476, 419, 36, 387, 518, 187, 261, 565, 192, 475, 310, 25, 117, 456, 277, 156, 474, 541, 299, 473, 485, 454, 402, 27, 425, 250, 410, 459, 307, 165, 43, 291, 515, 540, 366, 392, 15, 178, 28, 162, 458, 528, 378, 13, 188, 447, 539, 560, 288, 10, 359, 506, 47, 164, 211, 90, 493, 53, 492, 364, 103, 502, 251, 181, 51, 229, 534, 232, 365, 573, 513, 393, 577, 350, 443, 535, 327, 93, 415, 303, 582, 526, 148, 281, 558, 166, 142, 149, 337, 74, 102, 432, 160, 55, 302, 314, 92, 486, 360, 434, 391, 171, 114, 325, 512, 228, 549, 167, 276, 63, 550, 490, 298, 547, 118, 158, 44, 452, 349, 249, 559, 177, 135, 199, 499, 400, 488, 583, 529, 384, 338, 289, 295, 414, 98, 355, 222, 342, 553, 131, 306, 469, 57, 54, 407, 329, 448, 262, 129, 257, 75, 328, 322, 491, 48, 330, 151, 315, 231, 287, 235, 575, 122, 116, 16, 401, 596, 253, 484, 81, 109, 300, 572, 483, 236, 285, 227, 405, 33, 29, 367, 381, 388, 224, 230, 352, 426, 424, 348, 123, 430, 275, 145, 395, 545, 238, 240, 422, 379, 527, 198, 494, 576, 153, 510, 427, 311, 312, 263, 403, 252, 470, 246, 101, 340, 176, 319, 435, 538, 72, 509, 71, 268, 205, 514, 477, 313, 320, 215, 76, 555, 67, 495, 49, 62, 568, 345, 233, 110, 152, 24, 316, 115, 193, 444, 163, 358, 179, 265, 106, 569, 374, 304, 212, 543, 451, 578, 35, 286, 380, 19, 65, 37, 404, 533, 333, 239, 546, 82, 68, 324, 221, 428, 460, 377, 382, 128, 125, 383, 113, 209, 318, 356, 26, 416, 386, 283, 64, 155, 59, 213, 497, 22, 449, 588, 317, 104, 563, 42, 11, 574, 472, 154, 554, 353, 207, 399, 372, 457, 294, 525, 570, 489, 7, 272, 362, 189, 39, 184, 396, 408, 439, 83, 5, 390, 220, 412, 357, 557, 52, 301, 202, 462, 119, 368, 214, 376, 305, 519, 218, 186, 58, 256, 336, 31, 107, 137, 242, 173, 398, 157, 269, 280, 292, 453, 191, 196, 503, 520, 138, 266, 4, 30, 20, 351, 180, 441, 112, 517, 478, 334, 335, 585, 60, 551, 423, 172, 108, 466, 592, 511, 264, 487, 243, 516, 139, 38, 548, 69, 66, 385, 8, 308, 464, 370, 73, 332, 168, 1, 562, 579, 369, 247, 468, 481, 331, 599, 394, 341, 498, 463, 445, 96, 595, 593, 136, 480, 429, 536, 201, 234, 170, 84, 346, 505, 91, 417, 248, 254, 273, 598, 326, 323, 290, 56, 293, 86, 406, 6, 594, 79, 373, 80, 567, 537, 586, 244, 190, 174, 150, 581, 195, 507, 89, 530, 0, 46, 354, 3, 210, 169, 183, 2, 411, 130, 121, 589, 421, 144, 587, 77, 339, 182, 584, 127, 241, 120, 504, 97, 371, 87, 590, 271, 9, 140, 17, 88, 397, 496, 270, 40, 508, 225, 363, 389, 531, 70, 450, 284, 226, 344, 461, 85, 375, 99, 203, 552, 343, 206, 34, 95, 471, 321, 258, 501, 146, 14},
                {536, 149, 105, 201, 293, 287, 519, 351, 173, 205, 479, 532, 54, 375, 108, 88, 486, 509, 38, 307, 165, 86, 39, 130, 397, 373, 551, 454, 288, 273, 294, 176, 235, 43, 158, 32, 447, 586, 562, 468, 269, 429, 502, 353, 40, 483, 159, 82, 360, 122, 466, 301, 378, 226, 557, 94, 404, 463, 120, 592, 411, 333, 48, 185, 573, 145, 393, 318, 241, 448, 365, 298, 234, 537, 286, 17, 16, 425, 114, 4, 218, 53, 570, 238, 484, 255, 59, 263, 549, 200, 505, 199, 503, 264, 571, 392, 376, 270, 572, 438, 209, 15, 361, 249, 188, 528, 482, 419, 477, 487, 457, 246, 236, 476, 281, 349, 368, 66, 51, 354, 203, 589, 8, 123, 432, 475, 166, 46, 71, 462, 379, 137, 3, 117, 491, 470, 442, 543, 426, 110, 437, 471, 2, 11, 446, 474, 362, 257, 381, 325, 70, 566, 285, 400, 224, 344, 577, 143, 56, 248, 507, 556, 596, 403, 595, 363, 300, 568, 245, 433, 295, 289, 251, 356, 98, 63, 81, 545, 74, 409, 243, 329, 290, 142, 582, 597, 151, 428, 348, 31, 469, 230, 340, 69, 327, 109, 278, 67, 421, 460, 131, 342, 517, 144, 335, 489, 312, 75, 128, 591, 565, 480, 100, 587, 124, 371, 135, 183, 585, 332, 34, 497, 522, 30, 430, 530, 303, 29, 119, 336, 283, 7, 223, 79, 10, 232, 304, 306, 191, 178, 92, 377, 192, 187, 115, 33, 358, 44, 594, 550, 439, 127, 321, 168, 72, 531, 444, 116, 341, 282, 366, 113, 401, 407, 195, 242, 584, 588, 337, 276, 345, 311, 576, 435, 50, 508, 387, 396, 494, 314, 1, 451, 155, 316, 598, 338, 388, 247, 350, 500, 518, 181, 68, 61, 516, 542, 180, 323, 548, 481, 140, 328, 65, 534, 417, 45, 186, 579, 402, 499, 6, 538, 520, 525, 240, 284, 330, 593, 49, 147, 297, 445, 170, 280, 148, 440, 208, 102, 406, 485, 214, 495, 319, 129, 459, 367, 169, 52, 184, 136, 515, 315, 355, 28, 555, 352, 13, 488, 415, 564, 231, 405, 156, 213, 215, 125, 37, 233, 190, 347, 472, 527, 506, 95, 296, 42, 91, 317, 84, 322, 541, 21, 111, 18, 498, 370, 193, 359, 431, 464, 256, 141, 139, 343, 581, 189, 547, 99, 529, 260, 390, 558, 490, 427, 422, 374, 412, 64, 386, 369, 599, 202, 424, 560, 458, 73, 153, 453, 58, 121, 138, 302, 553, 96, 559, 277, 346, 89, 434, 55, 416, 253, 60, 133, 514, 455, 36, 258, 265, 334, 27, 132, 324, 76, 259, 535, 104, 197, 271, 408, 22, 554, 546, 292, 150, 291, 97, 171, 394, 389, 385, 326, 372, 441, 118, 395, 20, 222, 198, 309, 227, 473, 239, 162, 216, 413, 152, 35, 320, 172, 103, 478, 524, 496, 552, 308, 225, 80, 492, 452, 384, 398, 590, 539, 339, 174, 583, 511, 574, 450, 380, 540, 275, 78, 252, 493, 512, 126, 179, 279, 274, 164, 41, 157, 221, 305, 504, 0, 220, 272, 533, 175, 163, 237, 106, 83, 250, 134, 521, 267, 14, 194, 262, 211, 567, 219, 544, 501, 24, 62, 217, 310, 177, 513, 77, 19, 391, 57, 87, 420, 90, 410, 182, 364, 112, 146, 465, 436, 526, 268, 206, 244, 228, 456, 399, 229, 161, 569, 25, 414, 575, 12, 160, 561, 449, 299, 578, 207, 93, 467, 196, 254, 204, 510, 212, 26, 266, 5, 23, 357, 107, 580, 210, 9, 523, 461, 313, 423, 154, 331, 443, 85, 47, 382, 261, 563, 418, 101, 383, 167},
                {64, 215, 266, 277, 412, 456, 378, 417, 174, 420, 392, 422, 549, 250, 546, 482, 81, 560, 353, 559, 130, 430, 222, 555, 343, 146, 88, 536, 22, 125, 276, 257, 500, 455, 411, 149, 383, 85, 145, 69, 505, 77, 558, 400, 101, 72, 531, 562, 397, 91, 184, 71, 284, 351, 379, 136, 263, 301, 516, 265, 359, 211, 4, 183, 569, 135, 241, 578, 519, 325, 15, 371, 465, 30, 469, 377, 367, 120, 334, 425, 471, 180, 224, 249, 296, 102, 98, 214, 311, 23, 243, 14, 520, 150, 302, 182, 160, 179, 586, 591, 5, 494, 232, 433, 502, 79, 217, 49, 567, 115, 573, 327, 209, 480, 496, 498, 463, 426, 111, 544, 255, 484, 139, 443, 95, 415, 518, 40, 384, 563, 574, 541, 103, 452, 216, 231, 466, 229, 34, 571, 309, 347, 295, 310, 534, 275, 356, 75, 226, 59, 89, 497, 423, 330, 20, 460, 142, 332, 566, 83, 186, 342, 53, 365, 197, 360, 488, 517, 158, 344, 335, 405, 421, 128, 264, 408, 358, 26, 416, 552, 395, 188, 396, 234, 68, 116, 67, 288, 506, 478, 399, 554, 526, 491, 436, 138, 58, 144, 27, 177, 204, 473, 585, 189, 461, 202, 31, 54, 194, 187, 185, 291, 228, 388, 8, 50, 289, 294, 515, 472, 37, 331, 320, 414, 281, 218, 357, 486, 321, 372, 540, 52, 140, 315, 248, 164, 287, 529, 381, 225, 464, 380, 230, 285, 308, 348, 39, 195, 385, 548, 450, 435, 167, 55, 122, 326, 393, 134, 431, 577, 576, 474, 161, 297, 206, 261, 176, 170, 340, 352, 523, 355, 404, 346, 543, 36, 354, 329, 446, 485, 512, 550, 527, 532, 252, 389, 205, 107, 312, 1, 61, 300, 212, 339, 159, 476, 196, 513, 237, 106, 483, 499, 551, 175, 47, 593, 477, 442, 208, 119, 154, 178, 168, 447, 253, 349, 244, 368, 123, 373, 7, 240, 317, 501, 100, 292, 286, 409, 108, 80, 387, 84, 60, 137, 545, 104, 514, 207, 112, 24, 582, 153, 580, 588, 583, 589, 221, 451, 508, 413, 220, 401, 318, 440, 445, 429, 581, 147, 495, 376, 511, 299, 280, 306, 73, 475, 118, 213, 382, 199, 468, 233, 572, 97, 470, 124, 319, 246, 110, 370, 341, 86, 467, 245, 419, 132, 542, 3, 361, 181, 92, 398, 35, 597, 509, 490, 599, 117, 151, 504, 44, 459, 564, 462, 453, 403, 74, 539, 303, 278, 78, 258, 449, 590, 11, 305, 575, 362, 126, 279, 76, 336, 304, 570, 25, 90, 557, 171, 169, 391, 489, 535, 262, 448, 242, 561, 406, 62, 210, 190, 390, 328, 369, 528, 374, 366, 418, 254, 48, 274, 247, 239, 96, 66, 364, 481, 56, 148, 33, 10, 105, 338, 524, 444, 198, 333, 173, 492, 269, 439, 394, 162, 17, 322, 141, 43, 424, 553, 152, 57, 238, 260, 259, 272, 172, 537, 595, 487, 525, 375, 568, 283, 271, 127, 32, 521, 113, 363, 432, 437, 9, 503, 268, 592, 438, 223, 556, 273, 479, 293, 131, 191, 256, 193, 46, 12, 51, 428, 579, 203, 156, 454, 458, 251, 410, 18, 507, 282, 29, 584, 82, 0, 267, 337, 94, 192, 316, 201, 109, 45, 166, 522, 13, 350, 565, 41, 324, 63, 314, 236, 155, 42, 298, 587, 143, 2, 21, 129, 70, 402, 594, 235, 121, 538, 290, 65, 28, 598, 510, 596, 547, 133, 493, 270, 157, 16, 441, 533, 165, 530, 407, 386, 38, 200, 427, 219, 19, 457, 434, 87, 163, 6, 114, 99, 227, 313, 323, 307, 345, 93},

        };

        expected = new int[][]{
                {},
                {1},
                {1, 2},
                {1, 2},
                {1, 2, 3},
                {1, 2, 3},
                {1, 2, 3},
                {1, 2, 3},
                {1, 2, 3},
                {1, 2, 3},

                {1, 2, 3, 4}, {1, 2, 3, 4},
                {0, 1, 2, 3, 4}, {0, 1, 2, 3, 4},
                {0, 1, 2, 3, 4, 5}, {0, 1, 2, 3, 4, 5},
                {0, 1, 2, 3, 4, 5, 6}, {0, 1, 2, 3, 4, 5, 6},

                {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11}, {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11}, {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11}, {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11},
                {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23}, {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23}, {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23}, {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23},

                {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99},
                {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99},
                {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99},
                {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99},
                {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99},
                {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99},
                {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239, 240, 241, 242, 243, 244, 245, 246, 247, 248, 249, 250, 251, 252, 253, 254, 255, 256, 257, 258, 259, 260, 261, 262, 263, 264, 265, 266, 267, 268, 269, 270, 271, 272, 273, 274, 275, 276, 277, 278, 279, 280, 281, 282, 283, 284, 285, 286, 287, 288, 289, 290, 291, 292, 293, 294, 295, 296, 297, 298, 299, 300, 301, 302, 303, 304, 305, 306, 307, 308, 309, 310, 311, 312, 313, 314, 315, 316, 317, 318, 319, 320, 321, 322, 323, 324, 325, 326, 327, 328, 329, 330, 331, 332, 333, 334, 335, 336, 337, 338, 339, 340, 341, 342, 343, 344, 345, 346, 347, 348, 349, 350, 351, 352, 353, 354, 355, 356, 357, 358, 359, 360, 361, 362, 363, 364, 365, 366, 367, 368, 369, 370, 371, 372, 373, 374, 375, 376, 377, 378, 379, 380, 381, 382, 383, 384, 385, 386, 387, 388, 389, 390, 391, 392, 393, 394, 395, 396, 397, 398, 399, 400, 401, 402, 403, 404, 405, 406, 407, 408, 409, 410, 411, 412, 413, 414, 415, 416, 417, 418, 419, 420, 421, 422, 423, 424, 425, 426, 427, 428, 429, 430, 431, 432, 433, 434, 435, 436, 437, 438, 439, 440, 441, 442, 443, 444, 445, 446, 447, 448, 449, 450, 451, 452, 453, 454, 455, 456, 457, 458, 459, 460, 461, 462, 463, 464, 465, 466, 467, 468, 469, 470, 471, 472, 473, 474, 475, 476, 477, 478, 479, 480, 481, 482, 483, 484, 485, 486, 487, 488, 489, 490, 491, 492, 493, 494, 495, 496, 497, 498, 499, 500, 501, 502, 503, 504, 505, 506, 507, 508, 509, 510, 511, 512, 513, 514, 515, 516, 517, 518, 519, 520, 521, 522, 523, 524, 525, 526, 527, 528, 529, 530, 531, 532, 533, 534, 535, 536, 537, 538, 539, 540, 541, 542, 543, 544, 545, 546, 547, 548, 549, 550, 551, 552, 553, 554, 555, 556, 557, 558, 559, 560, 561, 562, 563, 564, 565, 566, 567, 568, 569, 570, 571, 572, 573, 574, 575, 576, 577, 578, 579, 580, 581, 582, 583, 584, 585, 586, 587, 588, 589, 590, 591, 592, 593, 594, 595, 596, 597, 598, 599},
                {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239, 240, 241, 242, 243, 244, 245, 246, 247, 248, 249, 250, 251, 252, 253, 254, 255, 256, 257, 258, 259, 260, 261, 262, 263, 264, 265, 266, 267, 268, 269, 270, 271, 272, 273, 274, 275, 276, 277, 278, 279, 280, 281, 282, 283, 284, 285, 286, 287, 288, 289, 290, 291, 292, 293, 294, 295, 296, 297, 298, 299, 300, 301, 302, 303, 304, 305, 306, 307, 308, 309, 310, 311, 312, 313, 314, 315, 316, 317, 318, 319, 320, 321, 322, 323, 324, 325, 326, 327, 328, 329, 330, 331, 332, 333, 334, 335, 336, 337, 338, 339, 340, 341, 342, 343, 344, 345, 346, 347, 348, 349, 350, 351, 352, 353, 354, 355, 356, 357, 358, 359, 360, 361, 362, 363, 364, 365, 366, 367, 368, 369, 370, 371, 372, 373, 374, 375, 376, 377, 378, 379, 380, 381, 382, 383, 384, 385, 386, 387, 388, 389, 390, 391, 392, 393, 394, 395, 396, 397, 398, 399, 400, 401, 402, 403, 404, 405, 406, 407, 408, 409, 410, 411, 412, 413, 414, 415, 416, 417, 418, 419, 420, 421, 422, 423, 424, 425, 426, 427, 428, 429, 430, 431, 432, 433, 434, 435, 436, 437, 438, 439, 440, 441, 442, 443, 444, 445, 446, 447, 448, 449, 450, 451, 452, 453, 454, 455, 456, 457, 458, 459, 460, 461, 462, 463, 464, 465, 466, 467, 468, 469, 470, 471, 472, 473, 474, 475, 476, 477, 478, 479, 480, 481, 482, 483, 484, 485, 486, 487, 488, 489, 490, 491, 492, 493, 494, 495, 496, 497, 498, 499, 500, 501, 502, 503, 504, 505, 506, 507, 508, 509, 510, 511, 512, 513, 514, 515, 516, 517, 518, 519, 520, 521, 522, 523, 524, 525, 526, 527, 528, 529, 530, 531, 532, 533, 534, 535, 536, 537, 538, 539, 540, 541, 542, 543, 544, 545, 546, 547, 548, 549, 550, 551, 552, 553, 554, 555, 556, 557, 558, 559, 560, 561, 562, 563, 564, 565, 566, 567, 568, 569, 570, 571, 572, 573, 574, 575, 576, 577, 578, 579, 580, 581, 582, 583, 584, 585, 586, 587, 588, 589, 590, 591, 592, 593, 594, 595, 596, 597, 598, 599},
                {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239, 240, 241, 242, 243, 244, 245, 246, 247, 248, 249, 250, 251, 252, 253, 254, 255, 256, 257, 258, 259, 260, 261, 262, 263, 264, 265, 266, 267, 268, 269, 270, 271, 272, 273, 274, 275, 276, 277, 278, 279, 280, 281, 282, 283, 284, 285, 286, 287, 288, 289, 290, 291, 292, 293, 294, 295, 296, 297, 298, 299, 300, 301, 302, 303, 304, 305, 306, 307, 308, 309, 310, 311, 312, 313, 314, 315, 316, 317, 318, 319, 320, 321, 322, 323, 324, 325, 326, 327, 328, 329, 330, 331, 332, 333, 334, 335, 336, 337, 338, 339, 340, 341, 342, 343, 344, 345, 346, 347, 348, 349, 350, 351, 352, 353, 354, 355, 356, 357, 358, 359, 360, 361, 362, 363, 364, 365, 366, 367, 368, 369, 370, 371, 372, 373, 374, 375, 376, 377, 378, 379, 380, 381, 382, 383, 384, 385, 386, 387, 388, 389, 390, 391, 392, 393, 394, 395, 396, 397, 398, 399, 400, 401, 402, 403, 404, 405, 406, 407, 408, 409, 410, 411, 412, 413, 414, 415, 416, 417, 418, 419, 420, 421, 422, 423, 424, 425, 426, 427, 428, 429, 430, 431, 432, 433, 434, 435, 436, 437, 438, 439, 440, 441, 442, 443, 444, 445, 446, 447, 448, 449, 450, 451, 452, 453, 454, 455, 456, 457, 458, 459, 460, 461, 462, 463, 464, 465, 466, 467, 468, 469, 470, 471, 472, 473, 474, 475, 476, 477, 478, 479, 480, 481, 482, 483, 484, 485, 486, 487, 488, 489, 490, 491, 492, 493, 494, 495, 496, 497, 498, 499, 500, 501, 502, 503, 504, 505, 506, 507, 508, 509, 510, 511, 512, 513, 514, 515, 516, 517, 518, 519, 520, 521, 522, 523, 524, 525, 526, 527, 528, 529, 530, 531, 532, 533, 534, 535, 536, 537, 538, 539, 540, 541, 542, 543, 544, 545, 546, 547, 548, 549, 550, 551, 552, 553, 554, 555, 556, 557, 558, 559, 560, 561, 562, 563, 564, 565, 566, 567, 568, 569, 570, 571, 572, 573, 574, 575, 576, 577, 578, 579, 580, 581, 582, 583, 584, 585, 586, 587, 588, 589, 590, 591, 592, 593, 594, 595, 596, 597, 598, 599},
        };
    }

    @Test
    public void quickSortIntTest() {
        for (int i = 0; i < arrays.length; i++) {
            QuickSort.quickSort(arrays[i]);
            assertArrayEquals(String.format("Test case #%d", i), expected[i], arrays[i]);
        }
    }

    @Test
    public void quickSortWithInsertionIntTest() {
        for (int i = 0; i < arrays.length; i++) {
            QuickSort.quickSortWithInsertion(arrays[i]);
            assertArrayEquals(String.format("Test case #%d", i), expected[i], arrays[i]);
        }
    }
}