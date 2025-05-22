const fs = require('fs');
const input = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
// const input = fs.readFileSync('./input.txt').toString().trim().split('\n');

const N = parseInt(input[0]);
const arr = [];
let sum = 0n; // 인구 합

for(let i = 1; i <= N; i++) {
  const [x, a] = input[i].split(' ').map(BigInt);
  arr.push([x, a]); // 위치, 인구 수
  sum += a;
}

arr.sort((o1, o2) => { // 위치 기준 오름차순 정렬
  if (o1[0] < o2[0]) return -1;
  if (o1[0] > o2[0]) return 1;
  return 0;
});

const mid = (sum + 1n) / 2n; // 합의 중앙값 구하기 
let total = 0n;
let answer = 0;

for(const [x, a] of arr) {
  total += a;
  if(total >= mid) {
    answer = x;
    break;
  }
}

console.log(answer.toString());