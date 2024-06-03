/**
  이거 무슨 문제인지 알아보기...
  건물 뭐시기?
*/

const solution = (heights) => {
  let answer = 0;
  const length = heights.length;
  const leftStack = [];
  const rightStack = [];

  for (let i = 0; i < length; i++) {
    // i = 0인 케이스
    if (leftStack.length === 0) leftStack.push(heights[i]);
    else {
      while (leftStack[leftStack.length - 1] <= heights[i]) {
        // 같은 경우만 +1
        if (leftStack[leftStack.length - 1] === heights[i]) answer++;
        // 나머지는 제거
        leftStack.pop();
      }
      answer += leftStack.length;
      leftStack.push(heights[i]);
      console.log('leftStack', i, leftStack);
      console.log('answer', answer);
    }
  }

  for (let i = length - 1; i >= 0; i--) {
    // i = length-1
    if (rightStack.length === 0) rightStack.push(heights[i]);
    else {
      while (rightStack[rightStack.length - 1] <= heights[i]) {
        if (rightStack[rightStack.length - 1] === heights[i]) answer++;
        rightStack.pop();
      }
      answer += rightStack.length;
      rightStack.push(heights[i]);
      console.log('rightStack', i, rightStack);
    }
  }
  return answer;
};

console.log(solution([1, 4, 2, 5, 3]));
console.log(solution([3, 4, 5, 6, 7]));
// console.log(solution([5, 5, 5]));
// console.log(solution([1, 99998, 99999]));
// console.log(solution([3, 5, 4, 2, 4, 4, 6, 5]));
// console.log(solution([1, 1, 2, 3, 1]));
// console.log(solution([1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11]));
