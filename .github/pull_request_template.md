## Summary
- 엔티티 기반 주문 도메인 초기 스캐폴딩 추가
- Product / Order / OrderItem 엔티티 및 연관관계 매핑
- 프로젝트 학습 목적 및 구조를 README에 문서화

## Changes
- `Product` 엔티티 추가
- `Order` 엔티티 추가
- `OrderItem` 엔티티 추가
- `OrderStatus` enum 추가
- `README.md` 추가
- `.github/pull_request_template.md` 추가

## Verification
- [x] `./gradlew compileJava`

## Notes
- 현재 단계는 엔티티 모델링 중심이며 API/Service/Repository는 후속 단계에서 확장 예정
