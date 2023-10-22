package com.pswida.library.discussion.application;

import com.pswida.library.catalog.application.core.cqs.command.CommandHandler;
import com.pswida.library.discussion.domain.Discussion;
import com.pswida.library.discussion.domain.DiscussionRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
class CreateDiscussionCommandHandler extends CommandHandler<CreateDiscussionCommand> {

  private final DiscussionRepository discussionRepository;

  private final ApplicationEventPublisher eventPublisher;

  @Override
  protected void doHandle(CreateDiscussionCommand command) {
    Discussion discussion = discussionRepository.getByOwnerId(command.ownerId())
      .map(this::restartDiscussion)
      .orElse(Discussion.newDiscussion(command.ownerId()));

    discussionRepository.save(discussion);
    discussion.getDomainEvents().forEach(eventPublisher::publishEvent);
  }

  @Override
  protected Class<CreateDiscussionCommand> commandType() {
    return CreateDiscussionCommand.class;
  }

  private Discussion restartDiscussion(Discussion discussion) {
    discussion.restartDiscussion();
    return discussion;
  }

}
